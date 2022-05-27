package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.Controller.AuthenticationController;
import com.revature.Controller.UserHandler;
import com.revature.Services.authorService;
import com.revature.Utilities.ConnectionFactoryUtility;

import io.javalin.Javalin;

public class Launcher {
	public static void main(String args[]) {
		UserHandler ec = new UserHandler();
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			System.out.println("Connection established");
		} catch(SQLException e) {
			System.out.println("Connection Failure");
			e.printStackTrace();
			
		}
		DriverClass menuLauncher = new DriverClass();
		menuLauncher.displayLoginMenu();
//		//javalin point paradox.
//		Javalin app = Javalin.create(
//		config -> {
//			config.enableCorsForAllOrigins();
//		}).start(3000);
//		//now for end points
//		app.get("/employee", ec.getUserHandler);
//		app.post("/employee", ec.insertUserHandler);
//		app.post("/login", ec.login);
	}
}
