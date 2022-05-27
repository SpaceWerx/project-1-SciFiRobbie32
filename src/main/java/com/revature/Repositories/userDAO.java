package com.revature.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Role;
import com.revature.Model.User;
import com.revature.Services.userService;
import com.revature.Utilities.ConnectionFactoryUtility;

public class userDAO {
	
	public static void insertUser(User newUser) throws SQLException {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			String sql = "insert into users (username, password, role)"+" values (?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql); 
			ps.setString(1, newUser.getUserName());
			ps.setString(2, newUser.getPassword());
			ps.setString(3, newUser.getRole().toString());
			ps.executeUpdate();
			System.out.println("User: "+newUser.getUserName()+" Created!");
		} catch(SQLException e) {
			System.out.println("An error occured while creating profile");
			e.printStackTrace();
		}
	}
	public User getUserById(int id){
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			ResultSet rs = null;
			String sql = "select * from users where id=?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Role role = null;
			
			User UserById = new User();
			rs.next();
			UserById.setId(rs.getInt("id"));
			UserById.setUserName(rs.getString("username"));
			UserById.setPassword(rs.getString("password"));
			switch(rs.getString("role")) {
			case "MANAGER":
				role = Role.MANAGER;
				break;
			case "EMPLOYEE":
				role = Role.EMPLOYEE;
				break;
			}
			return UserById;
		} catch(SQLException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
		return null;
		
	}
	public static boolean create(User userToBeRegistered) {
		try {
			insertUser(userToBeRegistered);
			return true;
		} catch(Exception e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
		return false;
	}
	public static User getByUserName(String userName) {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			ResultSet rs = null;
			String sql = "select * from users where username=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			Role role = null;
			
			User getUserByName = new User();
			rs.next();
			getUserByName.setId(rs.getInt("id"));
			getUserByName.setUserName(rs.getString("username"));
			getUserByName.setPassword(rs.getString("password"));
			switch(rs.getString("role")) {
			case "MANAGER":
				role = Role.MANAGER;
				break;
			case "EMPLOYEE":
				role = Role.EMPLOYEE;
				break;
			}
			getUserByName.setRole(role);
			return getUserByName;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<User> getUsers() {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			ResultSet rs = null;
			String sql = "select * from users;";
	
			Statement s = conn.createStatement();
			rs = s.executeQuery(sql);
			List<User> getUsers = new ArrayList<>();
			while(rs.next()) {
				User getUser = new User();
				getUser.setId(rs.getInt("id"));
				getUser.setUserName(rs.getString("username"));
				getUser.setPassword(rs.getString("password"));
				switch(rs.getString("role")) {
				case "Manager":
					getUser.setRole(Role.MANAGER);
					break;
				case "Employee:":
					getUser.setRole(Role.EMPLOYEE);
					break;
				}
				
				getUsers.add(getUser);
			}
			
			conn.close();
			return getUsers;
		} catch(SQLException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getByUserRole(Role role) {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			ResultSet rs = null;//initially set the result set
			String sql = "select * from users where role = ?;";//prepare the sql statement
			
			PreparedStatement ps = conn.prepareStatement(sql);//insert the prepared statement
			ps.setString(1, role.toString());
			rs = ps.executeQuery();//execute and get result
			List<User> getUsers = new ArrayList<>();
			while(rs.next()) {
				User getUser = new User();
				getUser.setId(rs.getInt("id"));
				getUser.setUserName(rs.getString("username"));
				getUser.setPassword(rs.getString("password"));
				switch(rs.getString("role")) {
				case "Manager":
					getUser.setRole(Role.MANAGER);
					break;
				case "Employee:":
					getUser.setRole(Role.EMPLOYEE);
					break;
				}
				getUsers.add(getUser);
				
			}
			
			conn.close();
			return getUsers;
		} catch(SQLException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
		return null;
	}
	
}
