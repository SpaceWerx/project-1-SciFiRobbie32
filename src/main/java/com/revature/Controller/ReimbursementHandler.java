package com.revature.Controller;

import java.util.List;
import com.google.gson.Gson;
import com.revature.Model.Reimbursement;
import com.revature.Repositories.ReimbursementDAO;

import io.javalin.http.Handler;

public class ReimbursementHandler {
	static ReimbursementDAO aDAO = new ReimbursementDAO();
	public Handler getReimbursements = (ctx ->{
		List<Reimbursement> allReimbursements = aDAO.getAllReimbursements();
		Gson gson = new Gson();
		String JSONObject = gson.toJson(allReimbursements);
		ctx.result(JSONObject);
		ctx.status();
		
	});
	
	public Handler getPendingReimbursements = (ctx ->{
		List<Reimbursement> pendingReimbusements = aDAO.getPendingReimbursement();
		String body = ctx.body();
		Gson gson = new Gson();
		String JSONObject = gson.toJson(pendingReimbusements);
		aDAO.getPendingReimbursement();
		ctx.result(JSONObject);
		ctx.status(201);
		
	});
	
	public Handler getResolvedReimbursements = (ctx ->{
		List<Reimbursement> resolvedReimbursements = aDAO.getResolvedReimbursements();
		String body = ctx.body();
		Gson gson = new Gson();
		String JSONObject = gson.toJson(resolvedReimbursements);
		aDAO.getPendingReimbursement();
		ctx.result(JSONObject);
		ctx.status(201);
		
	});
	
	public Handler createReimbursements = (ctx ->{
		String body = ctx.body();
		Gson gson = new Gson();
		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		aDAO.ReimbursementToBeSubmitted(reimbursement);
		ctx.result("Reimbursements Got");
		ctx.status(201);
		
	});
	
	public Handler updateReimbursements = (ctx ->{
		String body = ctx.body();
		Gson gson = new Gson();
		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		aDAO.update(reimbursement, reimbursement.getResolver(), reimbursement.getStatus());
		ctx.result("Reimbursements Got");
		ctx.status(201);
		
	});
}
