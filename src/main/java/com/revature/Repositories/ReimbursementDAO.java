package com.revature.Repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Reimbursement;
import com.revature.Model.Status;
import com.revature.Model.Type;
import com.revature.Utilities.ConnectionFactoryUtility;

public class ReimbursementDAO {
	static List<Reimbursement> reimbursements;
	public static void update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {
		//The unprocessed Reimbursement is processed and sent to the database with an updated status along with the manager id.
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			String sql = "update reimbursement set (resolver, status) values (?,?) where id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			unprocessedReimbursement.setResolver(resolverId); //set the managers id assaigned as the resolver id
			unprocessedReimbursement.setStatus(updatedStatus); //set the new status
			ps.setLong(1, unprocessedReimbursement.getResolver()); //used to fill the ? each int indicating which ? to fill.
			ps.setString(2, unprocessedReimbursement.getStatus().toString());//used to fill the ? each int indicating which ? to fill.
			ps.setLong(3, unprocessedReimbursement.getId());//used to fill the ? each int indicating which ? to fill.
			ps.execute();//execute order 66- er i mean execute the sql code now that it has the ? populated.
			System.out.println("User reimbursement Submitted!");
		} catch(SQLException e) {
			System.out.println("An error occured while creating profile");
			e.printStackTrace();
		}
		throw new RuntimeException("There was an error processing this reimbursement, please try again;");
		
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
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			String sql = "insert into reimbursement (id, author, resolver, description, type, status) values (?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setLong(1, reimbursementToBeSubmitted.getId());//used to fill the ? each int indicating which ? to fill.
			ps.setLong(2, reimbursementToBeSubmitted.getAuthor());//used to fill the ? each int indicating which ? to fill.
			ps.setLong(3, reimbursementToBeSubmitted.getResolver());//used to fill the ? each int indicating which ? to fill.
			ps.setString(4, reimbursementToBeSubmitted.getDescription());//used to fill the ? each int indicating which ? to fill.
			ps.setString(5, reimbursementToBeSubmitted.getType().toString());//used to fill the ? each int indicating which ? to fill.
			ps.setString(6, reimbursementToBeSubmitted.getStatus().toString());//used to fill the ? each int indicating which ? to fill.
			ps.execute();
			System.out.println("User reimbursement Submitted!");
		} catch(SQLException e) {
			System.out.println("An error occured while creating profile");
			e.printStackTrace();
		}
		
	}
	public static List<Reimbursement> getResolvedReimbursements() {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){			
			String sql = "select * from reimbursement where status = resolved;";	
			Statement st = conn.createStatement();	
			ResultSet rs = null;
			rs = st.executeQuery(sql);
			List<Reimbursement> resolvedReimbursements = new ArrayList<>();
			Status status = null;//set up for getting the enum status for the object creation below
			switch(rs.getString("Status")) {
			case "PENDING":
				status = Status.PENDING;
				break;
			case "APPROVED":
				status = Status.APPROVED;
				break;
			case "DENIED":
				status = Status.DENIED;
				break;
			}
			Type type = null;//set up for getting the enum type for the object creation below
			switch(rs.getString("Type")) {
			case "FOOD":
				type = Type.FOOD;
				break;
			case "LODGING":
				type = Type.LODGING;
				break;
			case "TRAVEL":
				type = Type.TRAVEL;
				break;
			case "OTHER":
				type = Type.OTHER;
				break;
			}
			while(rs.next()) {
				Reimbursement resolvedReimbursement = new Reimbursement(
						rs.getInt("id"),//used to get the result based off the database.
						rs.getInt("author"),//used to get the result based off the database.
						rs.getInt("resolver"),//used to get the result based off the database.
						rs.getString("description"),//used to get the result based off the database.
						type,
						status,
						rs.getLong("amount")//used to get the result based off the database.
						);
				resolvedReimbursements.add(resolvedReimbursement);
			}
			System.out.println("User reimbursement Submitted!");
			return resolvedReimbursements;
		} catch(SQLException e) {
			System.out.println("An error occured while creating profile");
			e.printStackTrace();
		}
		return null;
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
