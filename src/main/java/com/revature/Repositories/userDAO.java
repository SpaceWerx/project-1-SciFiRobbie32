package com.revature.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Role;
import com.revature.Model.User;
import com.revature.Services.userService;
import com.revature.Utilities.ConnectionFactoryUtility;

public class userDAO {
	
	public static void insertUser(User newUser) throws SQLException {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			String sql = "insert into users (username, password, role) " + "values (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setLong(1, newUser.getId());
			ps.setString(2, newUser.getUserName());
			ps.setString(3, newUser.getPassword());
			ps.setString(4, newUser.getRole().toString());
			
			ps.execute();
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
			rs = ps.executeQuery(sql);
			ps.setInt(1, id);
			User getUser;
			User user = new User();
			rs.getInt("user stuff here");
			getUser = user;
			return getUser;
		} catch(SQLException e) {
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
			String sql = "select * from users where values (?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			User getUser = null;
			ps.setString(1, userName);
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
			conn.close();
			return getUser;
		} catch(SQLException e) {
		}
		return null;
	}
	public List<User> getUsers() {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			ResultSet rs = null;
			String sql = "select * from users;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			List<User> getUsers = null;
			User getUser = null;
			while(rs.next()) {
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
		}
		return null;
	}
	public List<User> getByUserRole(Role role) {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			ResultSet rs = null;
			String sql = "select * from users where role = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			ps.setString(1, role.toString());
			List<User> getUsers = null;
			User getUser = null;
			while(rs.next()) {
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
		}
		return null;
	}
	public List<User> queryUserExistById(int id) {
		try(Connection conn = ConnectionFactoryUtility.getConnection()){
			ResultSet rs = null;
			String sql = "select * from users where id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			ps.setInt(1, id);
			List<User> getUsers = null;
			User getUser = null;
			while(rs.next()) {
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
		}
		return null;
	}
	
}
