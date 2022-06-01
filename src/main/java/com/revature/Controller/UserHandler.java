package com.revature.Controller;

import java.util.List;

import com.google.gson.Gson;
import com.revature.Model.User;
import com.revature.Services.UserService;

import io.javalin.http.Handler;

public class UserHandler {
	
	UserService es = new UserService();
	
	public Handler getAllUsers = (ctx ->{
		List<User> allEmployees = es.getAllUsers();
		Gson gson = new Gson();
		String JSONObject = gson.toJson(allEmployees);
		ctx.result(JSONObject);
		ctx.status();
	});

	public Handler insertUserHandler = (ctx ->{
		String body = ctx.body();
		Gson gson = new Gson();
		User user = gson.fromJson(body, User.class);
		es.addUser(user);
		String pmDebug = user.getId()+" "+user.getUserName()+" "+user.getPassword()+" "+user.getRole();
		ctx.result(pmDebug);
		ctx.status(201);
		
	});
	
	public Handler login = (ctx ->{
		String body = ctx.body();
		Gson gson = new Gson();
		User user = gson.fromJson(body, User.class);
		es.getUserByUsername(user.getUserName());
		ctx.result("Login Successful");
		ctx.status(201);
		
	});

}
