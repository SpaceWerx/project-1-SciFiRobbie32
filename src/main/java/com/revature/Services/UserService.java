package com.revature.Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Role;
import com.revature.Model.User;
import com.revature.Repositories.userDAO;

public class UserService {
	static userDAO aDAO = new userDAO();
	public static User getUserById(int author) {
		User users = aDAO.getUserById(author);
		return users;
	}
	public static User getUserByUsername(String name) {
		User users = aDAO.getByUserName(name);
		return users;
	}
	public static List<User> getUserByRole(Role role) {
		List<User> users = aDAO.getByUserRole(role);
		return users;
	}
	public static List<User> getAllUsers() {
		List<User> users = aDAO.getUsers();
		return users;
	}
//	public static List<User> queryUserExistById(int id) {
//		List<User> users = aDAO.queryUserExistById(id);
//		return users;
//	}
	public static void addUser(User user) throws SQLException{
		aDAO.insertUser(user);
		
	}
}
