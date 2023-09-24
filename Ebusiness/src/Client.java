import jade.core.AID;
import jade.core.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Object;
import jade.wrapper.*;
import jade.core.Agent;



public class Client extends JFrame{

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the frame.
	 */
	public Client() {
		
		setTitle("E-Business");
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("SELL");
		rdbtnNewRadioButton.setForeground(new Color(139, 0, 139));
		rdbtnNewRadioButton.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(264, 53, 119, 29);
		rdbtnNewRadioButton.setFont(new Font("Cambria", Font.BOLD, 16));
		
		final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("BUY");
		rdbtnNewRadioButton_1.setForeground(new Color(139, 0, 139));
		rdbtnNewRadioButton_1.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_1.setBounds(264, 112, 391, 29);
		rdbtnNewRadioButton_1.setFont(new Font("Cambria", Font.BOLD, 16));
		
				
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		
		
		rdbtnNewRadioButton.addActionListener(null);
		rdbtnNewRadioButton_1.addActionListener(null);
		
		
		JButton btnNewButton = new JButton("DONE");
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(200, 357, 147, 42);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectionString="";
				if(rdbtnNewRadioButton.isSelected())
					selectionString+="SELL";					
				if(rdbtnNewRadioButton_1.isSelected())
					selectionString+="BUY";
				
				if(selectionString.equals("SELL"))
				{
					Seller sell=new Seller();
					dispose();
				//	sell.setVisible(true);
				
				
				}
					
					
					
					
					
					
					
					
					
					
		//			dispose();
		//			sell.setVisible(true);
				
			
				if(selectionString.equals("BUY"))
				{
			//		Buyer buy=new Buyer();
			
					
					
					
					
					
					
		//			dispose();
		//			buy.setVisible(true);
				}
				
				//JOptionPane.showMessageDialog(null,selectionString);
			}
		});
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 18));
		
		JLabel lblNewLabel = new JLabel("CHOOSE THE ACTION TO BE PERFORME");
		lblNewLabel.setBounds(0, 0, 801, 505);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 18));
		contentPane.setLayout(null);
		contentPane.add(rdbtnNewRadioButton);
		contentPane.add(rdbtnNewRadioButton_1);
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		contentPane.add(lblNewLabel, BorderLayout.CENTER);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	User ac = new User();
				dispose();
			//	ac.setVisible(true);
				
			}
		});
		btnBack.setFont(new Font("Calibri", Font.BOLD, 19));
		btnBack.setBounds(351, 370, 125, 35);
		contentPane.add(btnBack);
		
	/*	JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/loginbackground.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 801, 492);
		contentPane.add(label);
	*/
	}
		
	}



