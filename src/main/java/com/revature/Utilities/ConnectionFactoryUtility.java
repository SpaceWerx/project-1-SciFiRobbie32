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
    		Class.forName("org.postgresql.Driver"); //try to find and return the psotgresql Driver Class
    	} catch (ClassNotFoundException e) {
    		System.out.println("Class wasn't found.");
    		e.printStackTrace(); //this will print the exception message to the console
    	}

		String url = "jdbc:postgresql://dbp-1.cajgurwleadc.us-east-2.rds.amazonaws.com:5432/DatabaseP1?currentSchema=project1";
		String username = "robertP1";
		String password = "password";
		return DriverManager.getConnection(url, username, password);
	}
}
