package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.Controller.AuthenticationController;
import com.revature.Controller.UserHandler;
import com.revature.Controller.ReimbursementHandler;
import com.revature.Services.authorService;
import com.revature.Utilities.ConnectionFactoryUtility;

import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;

public class Launcher {
	public static void main(String args[]) {
		
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			System.out.println("Connection established");
		} catch(SQLException e) {
			System.out.println("Connection Failure");
			e.printStackTrace();
			
		}
		boolean val = true;
		while(val) {
			Scanner scan = new Scanner(System.in);
			System.out.println("would you like to launch CLI or Javalin?");
			System.out.println("Y/N");
			String s = scan.nextLine().toString();
		
			if(s.toUpperCase().equals("Y")) {
				DriverClass menuLauncher = new DriverClass();
				menuLauncher.displayLoginMenu();
				val=false;
			} else if(s.toUpperCase().equals("N")) {
//			Javalin point paradox.
				UserHandler ec = new UserHandler();
				ReimbursementHandler rc = new ReimbursementHandler();
				Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins();
				}).start(3000);
//					now for end points
					app.get("/getUsers", ec.getAllUsers);
					
					app.post("/login", ec.login);
					
					app.post("/register", ec.insertUserHandler);
					
					app.get("/getReimbursements", rc.getReimbursements);
					app.get("/getPendingReimbursements", rc.getPendingReimbursements);
					app.post("/createReimbursements", rc.createReimbursements);
					app.post("/updateReimbursement", rc.updateReimbursements);
					val=false;
				} else {
					System.out.println("Please Choose Y or N");
				}
			}
	}
}
