package com.revature.Controller;

import java.util.List;

import com.google.gson.Gson;
import com.revature.Model.User;
import com.revature.Services.userService;

import io.javalin.http.Handler;

public class UserHandler {
	userService es = new userService();
	public Handler getUserHandler = (ctx ->{
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
		ctx.result("User was created");
		ctx.status(201);
		
	});
	
	public Handler login = (ctx ->{
		String body = ctx.body();
		Gson gson = new Gson();
		User user = gson.fromJson(body, User.class);
		es.getUserByUsername(user.getUserName());
		ctx.result("User was created");
		ctx.status(201);
		
	});

}
