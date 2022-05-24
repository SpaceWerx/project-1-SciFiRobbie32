package com.revature.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryUtility {
	private static ConnectionFactoryUtility instance;
	
	public static ConnectionFactoryUtility getInstance() {
		if(instance == null) {
			instance = new ConnectionFactoryUtility();
		}
		return instance;
	}
	
	public static Connection getConnection() throws SQLException{
		try {
		//Connection conn = DriverManager.getConnection("dbp-1.cajgurwleadc.us-east-2.rds.amazonaws.com","robertP1","password");
		Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("A Connection Error has occured.");
			e.printStackTrace();
		}
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=Users";
		String username = "robertP1";
		String password = "password";
		return DriverManager.getConnection(url, username, password);
	}
}
