package com.revature.Services;

import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Reimbursement;
import com.revature.Model.Status;
import com.revature.Repositories.ReimbursementDAO;

public class rService {
	static ReimbursementDAO rDAO = new ReimbursementDAO();
	static List<Reimbursement> reimbursements;
	public static void update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {
		rDAO.update(unprocessedReimbursement, resolverId, updatedStatus);
		
	}
	public static Reimbursement getReimbursementById(int id) {
		return rDAO.getReimbursementById(id);
		
	}
	public static void submitReimbursement(Reimbursement reimbursementToBeSubmitted) {
		rDAO.ReimbursementToBeSubmitted(reimbursementToBeSubmitted);
		
	}
	public static List<Reimbursement> getResolvedReimbursements() {
		return rDAO.getResolvedReimbursements();
	}
	public static List<Reimbursement> getPendingReimbursement() {
		return rDAO.getPendingReimbursement();
	}
	public static List<Reimbursement> getReimbursementsByAuthor(int userId) {
		return rDAO.getReimbursementsByAuthor(userId);
	}

	

}
