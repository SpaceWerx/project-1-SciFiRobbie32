package com.revature.Services;

import java.util.List;

import com.revature.Model.User;
import com.revature.Repositories.userDAO;

public class authorService {
	public static boolean register(User userToBeRegistered) {
		if(userDAO.getByUserName(userToBeRegistered.getUserName()) != null) {
			throw new NullPointerException("Username is already taken");
		}
		return userDAO.create(userToBeRegistered);
	}
	
	public User login(String username, String password) {
		User user;
		try {
			user = userDAO.getByUserName(username);
			if(user != null && password.equals(user.getPassword())) {
				return user;
			} else if(user != null && !password.equals(user.getPassword())) {
				System.out.println("Inncorrect Password");
				return null;
			} else {
				System.out.println("User doesn't exist.");
				return null;
			}
		} catch(Exception e) {
			System.out.println("Login insuccessful.");
			e.printStackTrace();
		}
		return null;
	}

}
