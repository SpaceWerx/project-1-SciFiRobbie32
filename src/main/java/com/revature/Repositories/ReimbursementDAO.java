package com.revature.Repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Reimbursement;
import com.revature.Model.Status;
import com.revature.Model.Type;
import com.revature.Utilities.ConnectionFactoryUtility;

public class ReimbursementDAO {
	public static void update(Reimbursement unprocessedReimbursement, int resolverId, Status updatedStatus) {
		//The unprocessed Reimbursement is processed and sent to the database with an updated status along with the manager id.
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			String sql = "update reimbursements set resolver = ?, status = ? where id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			unprocessedReimbursement.setResolver(resolverId); //set the managers id assaigned as the resolver id
			unprocessedReimbursement.setStatus(updatedStatus); //set the new status
			ps.setLong(1, unprocessedReimbursement.getResolver()); //used to fill the ? each int indicating which ? to fill.
			ps.setString(2, unprocessedReimbursement.getStatus().toString());//used to fill the ? each int indicating which ? to fill.
			ps.setLong(3, unprocessedReimbursement.getId());//used to fill the ? each int indicating which ? to fill.
			ps.execute();//execute order 66- er i mean execute the sql code now that it has the ? populated.
			System.out.println("User reimbursement Updated!");
		} catch(SQLException e) {
			System.out.println("An error occured while Updating");
			e.printStackTrace();
		}
		
	}
	public static Reimbursement getReimbursementById(int id) {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			ResultSet rs = null;
			String sql = "select * from reimbursements where id="+id+";";
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			rs.next();
			System.out.println("Reimbursement Succsesfully Requested!");
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
			Reimbursement ReimbursementById = new Reimbursement(
					rs.getInt("id"),//used to get the result based off the database.
					rs.getInt("author"),//used to get the result based off the database.
					rs.getInt("resolver"),//used to get the result based off the database.
					rs.getString("description"),//used to get the result based off the database.
					type,
					status,
					rs.getDouble("amount")//used to get the result based off the database.
					);
			return ReimbursementById;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void ReimbursementToBeSubmitted(Reimbursement reimbursementToBeSubmitted) {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			String sql = "insert into reimbursements (author, description, type, status, amount) values (?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, reimbursementToBeSubmitted.getAuthor());//used to fill the ? each int indicating which ? to fill.
			ps.setString(2, reimbursementToBeSubmitted.getDescription());//used to fill the ? each int indicating which ? to fill.
			ps.setString(3, reimbursementToBeSubmitted.getType().toString());//used to fill the ? each int indicating which ? to fill.
			ps.setString(4, "PENDING");//used to fill the ? each int indicating which ? to fill.
//			double val = reimbursementToBeSubmitted.getAmount();
//		    String[] ar=String.valueOf(val).split("\\.");
//			int dollars = Integer.parseInt(ar[0]);
//			int cents = Integer.parseInt(ar[1]);
//			String fullAMT = dollars+"."+cents;
			ps.setDouble(5, reimbursementToBeSubmitted.getAmount());//used to fill the ? each int indicating which ? to fill.
			ps.execute();
			System.out.println("User reimbursement Submitted!");
		} catch(SQLException e) {
			System.out.println("An error occured while creating profile");
			e.printStackTrace();
		}
		
	}
	public static List<Reimbursement> getResolvedReimbursements() {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){			
			String sql = "select * from reimbursements where status != 'PENDING';";	
			Statement st = conn.createStatement();	
			ResultSet rs = null;
			rs = st.executeQuery(sql);
			List<Reimbursement> resolvedReimbursements = new ArrayList<>();
			while(rs.next()) {
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
				Reimbursement resolvedReimbursement = new Reimbursement(
						rs.getInt("id"),//used to get the result based off the database.
						rs.getInt("author"),//used to get the result based off the database.
						rs.getInt("resolver"),//used to get the result based off the database.
						rs.getString("description"),//used to get the result based off the database.
						type,
						status,
						rs.getDouble("amount")//used to get the result based off the database.
						);
				resolvedReimbursements.add(resolvedReimbursement);
			}
			return resolvedReimbursements;
		} catch(SQLException e) {
		}
		return null;
	}
	public static List<Reimbursement> getPendingReimbursement() {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			ResultSet rs = null;
			String sql = "select * from reimbursements where status = 'PENDING';";
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			System.out.println("Reimbursement Succsesfully Requested!");
			List<Reimbursement> pendingReimbursement = new ArrayList<>();
			while(rs.next()) {
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
				Reimbursement resolvedReimbursement = new Reimbursement(
						rs.getInt("id"),//used to get the result based off the database.
						rs.getInt("author"),//used to get the result based off the database.
						rs.getInt("resolver"),//used to get the result based off the database.
						rs.getString("description"),//used to get the result based off the database.
						type,
						Status.PENDING,
						rs.getDouble("amount")//used to get the result based off the database.
						);
				pendingReimbursement.add(resolvedReimbursement);
			}
			return pendingReimbursement;
		} catch(SQLException e) {
		}
		return null;
	}
	public static List<Reimbursement> getReimbursementsByAuthor(int userId) {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			ResultSet rs = null;
			String sql = "select * from reimbursements where author="+userId+";";
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			System.out.println("Reimbursement Succsesfully Requested!");
			List<Reimbursement> userReimbursements = new ArrayList<>();
			while(rs.next()) {
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
				Reimbursement resolvedReimbursement = new Reimbursement(
						rs.getInt("id"),//used to get the result based off the database.
						rs.getInt("author"),//used to get the result based off the database.
						rs.getInt("resolver"),//used to get the result based off the database.
						rs.getString("description"),//used to get the result based off the database.
						type,
						status,
						rs.getDouble("amount")//used to get the result based off the database.
						);
				userReimbursements.add(resolvedReimbursement);
			}
			return userReimbursements;
		} catch(SQLException e) {
		}
		return null;
	}
}
