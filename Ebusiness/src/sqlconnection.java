import java.sql.*;

import javax.swing.*;


public class sqlconnection {

	Connection conn=null;
	
	public static Connection dbConnector()
	{
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\r\\workspace\\Ebusiness\\EBUSINESS.sqlite");
		//	JOptionPane.showMessageDialog(null,"Connection is successfull");
			return conn;
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
	}
	
	
}
