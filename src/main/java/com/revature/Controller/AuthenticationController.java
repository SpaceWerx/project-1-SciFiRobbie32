package com.revature.Controller;

import com.google.gson.Gson;
import com.revature.Model.User;
import com.revature.Services.authorService;

import io.javalin.http.Handler;

public class AuthenticationController {
	authorService as = new authorService();
	public Handler loginHandler = (ctx)->{
		
		String body = ctx.body();
		Gson gson = new Gson();
		User login = gson.fromJson(body, User.class);
		if(as.login(login.getUserName(), login.getPassword())) {
			String rt = null;
			switch(login.getRole()) {
			case EMPLOYEE:
				rt = "Employee";
				break;
			case MANAGER:
				rt = "Manager";
				break;
			}
			ctx.status();
			ctx.result(rt+" Login Sucsessful.");
			//use the portal based off of rt which is just role type as a string
		}
	};
}
