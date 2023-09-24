


import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class BuyerAgent extends Agent {
	
	private String targetTitle;
	
	private AID[] sellerAgents ;
	//private SellerAgent myGui; //s

	
	
	
	protected void setup() {
		
		System.out.println("Hello! Buyer-agent "+getAID().getName()+" is ready.");
		
		
			
		Object[] args = getArguments();	
		if (args != null && args.length > 0) {
			targetTitle = (String) args[0];
			System.out.println("Target product is "+targetTitle);

			
			addBehaviour(new TickerBehaviour(this, 60000) {
				protected void onTick() {
					myAgent.addBehaviour(new RequestPerformer());		//s
					System.out.println("Trying to buy " + targetTitle);
					DFAgentDescription template = new DFAgentDescription();
					ServiceDescription sd = new ServiceDescription();
					sd.setType("Product-selling");
					template.addServices(sd);
					try {
						DFAgentDescription[] result = DFService.search(myAgent, template); 
						System.out.println("Found the following seller agents:");
						sellerAgents = new AID[result.length];
						for (int i = 0; i < result.length; ++i) {
							sellerAgents[i] = result[i].getName();
							System.out.println(sellerAgents[i].getName());
						}
					}
					catch (FIPAException fe) {
						fe.printStackTrace();
					}

					
					myAgent.addBehaviour(new RequestPerformer());
				}
			} );		
		}
		else {
			
			System.out.println("No target product title specified");
			doDelete();
		}
	}

	
	protected void takeDown() {
		
		System.out.println("Buyer-agent "+getAID().getName()+" terminating.");
	}

	
	private class RequestPerformer extends Behaviour {
		private AID bestSeller;  
		private int bestPrice;  
		private int repliesCnt = 0; 
		private MessageTemplate mt; 
		private int step = 0;

		public void action() {
			switch (step) {
			case 0:
				
				ACLMessage cfp = new ACLMessage(ACLMessage.CFP);		
				for (int i = 0; i < sellerAgents.length; ++i) {
					cfp.addReceiver(sellerAgents[i]);
				} 
				cfp.setContent(targetTitle);
				cfp.setConversationId("Product-trade");
				cfp.setReplyWith("cfp"+System.currentTimeMillis()); 
				myAgent.send(cfp);				
				
				mt = MessageTemplate.and(MessageTemplate.MatchConversationId("Product-trade"),
						MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
				step = 1;
				break;
			case 1:
				
				ACLMessage reply = myAgent.receive(mt);
				if (reply != null) {
					
					if (reply.getPerformative() == ACLMessage.PROPOSE) {
						 
						int price = Integer.parseInt(reply.getContent());
						if (bestSeller == null || price < bestPrice) {
							
							bestPrice = price;
							bestSeller = reply.getSender();
						}
					}
					repliesCnt++;
					if (repliesCnt >= sellerAgents.length) {
						
						step = 2; 
					}
				}
				else {
					block();
				}
				break;
			case 2:
				
				ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
				order.addReceiver(bestSeller);
				order.setContent(targetTitle);
				order.setConversationId("Product-trade");
				order.setReplyWith("order"+System.currentTimeMillis());
				myAgent.send(order);
				
				mt = MessageTemplate.and(MessageTemplate.MatchConversationId("Product-trade"),
						MessageTemplate.MatchInReplyTo(order.getReplyWith()));
				step = 3;
				break;
			case 3:      
				
				reply = myAgent.receive(mt);
				if (reply != null) {
					
					if (reply.getPerformative() == ACLMessage.INFORM) {
						
						System.out.println(targetTitle + " successfully purchased from agent "+reply.getSender().getName());
						System.out.println("Price = " + bestPrice);
						myAgent.doDelete();
					}
					else {
						System.out.println("Attempt failed: requested product already sold.");
					}

					step = 4;
				}
				else {
					block();
				}
				break;
			}        
		}

		public boolean done() {
			if (step == 2 && bestSeller == null) {
				System.out.println("Attempt failed: "+targetTitle+" not available for sale");
			}
			return ((step == 2 && bestSeller == null) || step == 4);
		}
	}  
}
