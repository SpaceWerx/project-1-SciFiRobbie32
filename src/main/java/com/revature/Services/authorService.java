package com.revature.Services;

import com.revature.Model.User;
import com.revature.Repositories.userDAO;

public class authorService {
	public static boolean register(User userToBeRegistered) {
		if(userDAO.getByUserName(userToBeRegistered.getUserName()) != null) {
			System.out.println("Username is already taken");
			return false;
		}
		return userDAO.create(userToBeRegistered);
	}
	
	public boolean login(String username, String password) {
		User user = null;
		try {
			user = userDAO.getByUserName(username);
			if(user != null && password.equals(user.getPassword())) {
				return true;
			} else if(user != null && !password.equals(user.getPassword())) {
				System.out.println("Inncorrect Password");
				return false;
			} else {
				System.out.println("User doesn't exist.");
				return false;
			}
		} catch(Exception e) {
			System.out.println("Login insuccessful.");
			e.printStackTrace();
		}
		return false;
	}

}
