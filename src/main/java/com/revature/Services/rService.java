package com.revature.Services;

import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Reimbursement;
import com.revature.Model.Status;

public class rService {
	static List<Reimbursement> reimbursements;
	public static void update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {
		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getId() == unprocessedReimbursement.getId()) {
				reimbursement.setResolver(resolverId);
				reimbursement.setStatus(updatedStatus);
				return;
			}
		}
		throw new RuntimeException("There was an error processing this reimbursement, please try again");
		
	}
	public static Reimbursement getReimbursementById(int id) {
		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getId() ==  id) {
				return reimbursement;
			}
			
		}
		return null;
	}
	public static void sumbmitReimbursement(Reimbursement reimbursementToBeSubmitted) {
		
		Reimbursement latestReimbursement = reimbursements.get(reimbursements.size()-1);
		int id = latestReimbursement.getId()+1;
		reimbursementToBeSubmitted.setId(id);
		reimbursementToBeSubmitted.setStatus(Status.PENDING);
		reimbursements.add(reimbursementToBeSubmitted);
		
	}
	public static List<Reimbursement> getResolvedReimbursements() {
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();
		for(Reimbursement reimbursement :reimbursements) {
			if(reimbursement.getStatus() == Status.APPROVED || reimbursement.getStatus() == Status.DENIED) {
				resolvedReimbursements.add(reimbursement);
			}
			
		}
		return resolvedReimbursements;
	}
	public static List<Reimbursement> getPendingReimbursement() {
		List<Reimbursement> pendingReimbursement = new ArrayList<>();
		for(Reimbursement reimbursement :reimbursements) {
			if(reimbursement.getStatus() ==  Status.PENDING) {
				pendingReimbursement.add(reimbursement);
			}
			
		}
		return pendingReimbursement;
	}
	public static List<Reimbursement> getReimbursementsByAuthor(int userId) {
		List<Reimbursement> userReimbursement = new ArrayList<>();
		for(Reimbursement r :reimbursements) {
			if(r.getAuthor() ==  userId || r.getResolver() == userId) {
				userReimbursement.add(r);
			}
			
		}
		return userReimbursement;
	}

	

}
