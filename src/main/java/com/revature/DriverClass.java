package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.Model.Reimbursement;
import com.revature.Model.Role;
import com.revature.Model.Status;
import com.revature.Model.Type;
import com.revature.Model.User;
import com.revature.Services.authorService;
import com.revature.Services.rService;
import com.revature.Services.userService;

public class DriverClass {
	private Scanner scan = new Scanner(System.in);
	
	
	public void submitReimbursement(User employee) {	
		Reimbursement reimbursementToBeSubmitted = new Reimbursement();
		reimbursementToBeSubmitted.setAuthor(employee.getId());
		System.out.print("What type of reimbursement would you like to submit?");
		System.out.print("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
		System.out.print("1 -> Lodging");
		System.out.print("2 -> Travel");
		System.out.print("3 -> Food");
		System.out.print("4 -> Other");
		int typeDecision = promptSelection(1,2,3,4);
		
		switch(typeDecision) {
		case 1:
			reimbursementToBeSubmitted.setType(Type.LODGING);
			break;
		case 2:
			reimbursementToBeSubmitted.setType(Type.TRAVEL);
			break;
		case 3:
			reimbursementToBeSubmitted.setType(Type.FOOD);
			break;
		case 4:
			reimbursementToBeSubmitted.setType(Type.OTHER);
			break;
		}
		System.out.println("Please enter the dollar amount you are requesting to be reimbursed: ");
		System.out.println("$");
		reimbursementToBeSubmitted.setAmount(Double.parseDouble(fetchInput()));
		if (reimbursementToBeSubmitted.getAmount() <= 0) {
			System.out.println("Invalid Amount has been entered, please input a correct dollar amount.");
			boolean valid = false;
			while(!valid) {
				reimbursementToBeSubmitted.setAmount(Double.parseDouble(fetchInput()));
				if (reimbursementToBeSubmitted.getAmount() <= 0) {
					valid = true;
				}
			}
		}
		System.out.println("Please enter a description/reason for your reimbursement request: ");
		reimbursementToBeSubmitted.setDescription(scan.nextLine());
		if(reimbursementToBeSubmitted.getDescription().trim().equals("")) {
			System.out.println("Please enter a description/reason for your reimbursement request: ");
			boolean valid = false;
			while(!valid) {
				reimbursementToBeSubmitted.setDescription(scan.nextLine());
				if(!reimbursementToBeSubmitted.getDescription().trim().equals("")) {
					valid = true;
				}
			}
		}
		rService.sumbmitReimbursement(reimbursementToBeSubmitted);
	}
	
	public void processReimbursement(User manager) {
		boolean processPortal = true;
		System.out.println("--------------------------------------------------");
		System.out.println("Welcome to the Manager Processing Portal, "+manager.getUserName());
		System.out.println("--------------------------------------------------");
		System.out.println();
		
		while(processPortal) {
			List<Reimbursement> reimbursement = rService.getPendingReimbursement();
			if(reimbursement.isEmpty()) {
				System.out.println("There are no reimbursements to process.");
				System.out.println("Returning to previous menu...");
				return;
			}
			int[] ids = new int[reimbursement.size()];
			for(int i=0;i<reimbursement.size();i++) {
				Reimbursement r = reimbursement.get(i);
				User author = userService.getUserById(r.getAuthor());
				System.out.println(r.getId()+" -> "+author.getUserName()+" :$"+r.getAmount());
				ids[i] = r.getId();
				
			}
			System.out.println("Please enter the ID of the Reimbursement you wish to process.");
			int selection = promptSelection(ids);
			Reimbursement reimbursementToBeProcessed = rService.getReimbursementById(selection);
			System.out.println("Processing reimbursement #"+reimbursementToBeProcessed.getId());
			System.out.println("Details\nAuthor: "
				+ userService.getUserById(reimbursementToBeProcessed.getAuthor()).getUserName()
				+"\nAmount: " +reimbursementToBeProcessed.getAmount()
				+"\nDescription: " +reimbursementToBeProcessed.getDescription()
			);
			System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> Approved");
			System.out.println("2 -> Deny");
			
			int decision = promptSelection(1,2);
			Status status = (decision == 1) ? Status.APPROVED : Status.DENIED;
			rService.update(reimbursementToBeProcessed, manager.getId(), status);
			
			System.out.println("Would you like to process another reimbursement?");
			System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> Yes");
			System.out.println("2 -> No");
			
			int lastChoice = promptSelection(1,2);
			if(lastChoice == 2) {
				processPortal = false;
			}
		}
	}
	
	public void handlePortal(Role role) {
		List<User> users = userService.getUserByRole(role);
		int[] ids = new int[users.size() + 1];
		ids[users.size()] = 0;
		for(int i=0;i<users.size();i++) {
			ids[i] = users.get(i).getId();
		}
		
		System.out.println("---------------------------------------------------");
		System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
		for(User u:users) {
			System.out.println(u.getId()+" -> "+u.getUserName());
		}
		System.out.println("0 -> Return to Main Menu");
		System.out.println();
		int userChoice = promptSelection(ids);
		if (userChoice == 0) {
			return;
		}
		User employee = userService.getUserById(userChoice);
		if(role == Role.MANAGER) {
			System.out.println("Opening Manager Portal for "+employee.getUserName());
			displayFinanceManagerMenu(employee);
		} else {
			System.out.println("Opening Employee Portal for "+employee.getUserName());
			displayEmployeeMenu(employee);
		}
		
	}
	
//	@param validEntries the valid integer values the user must choose
//	@return the selected integer
	public int promptSelection(int ...validEntries) {
		int input;
		boolean valid = false;
		do {
			input = parsIntegerInput(fetchInput());
			for (int entry : validEntries) {
				if (entry == input) {
					valid = true;
					break;
				}
			}
		} while(!valid);
		return input;
	}
	
	public int parsIntegerInput(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("The input was malformed, please try again.");
			return -1;
		}
	}
	
	public Double parsDoubleInput(String input) {
		try {
			return Double.parseDouble(input);
		} catch (NumberFormatException e) {
			System.out.println("The input was malformed, please try again.");
			return -1.0;
		}
	}
	
	public String fetchInput() {
		//scan.nextLine obtains the entire line, such as "123 456"
		//split() turns it into an array seperated by whitespace, such as ["123","456"]
		//[0] keeps the first element only, leaving "123"
		return scan.nextLine().split(" ")[0];
	}
	
	public void displayPendingReimbursements() {
		List<Reimbursement> pendingReimbursements = rService.getPendingReimbursement();
		if(pendingReimbursements.isEmpty()) {
			System.out.println("No Pending Reimbursements...");
			System.out.println("Returning to Previous Menu...");
		}
		for(Reimbursement r: pendingReimbursements) {
			System.out.println(r);
		}
	}
	
	public void displayResolvedReimbursements() {
		List<Reimbursement> resolvedReimbursements = rService.getResolvedReimbursements();
		if(resolvedReimbursements.isEmpty()) {
			System.out.println("No Resolved Reimbursements...");
			System.out.println("Returning to Previous Menu...");
		}
		for(Reimbursement r: resolvedReimbursements) {
			System.out.println(r);
		}
	}
	
	public void displayPreviousRequests(User employee) {
		List<Reimbursement> reimbursement = rService.getReimbursementsByAuthor(employee.getId());
		if(reimbursement.isEmpty()) {
			System.out.println("No Previous Reimbursements...");
			System.out.println("Returning to Previous Menu...");
		}
		for(Reimbursement r: reimbursement) {
			System.out.println(r);
		}
	}
	
	public void displayMenu() {
		boolean menuOptions = true;
		
		System.out.println("---------------------------------------------------------------"); 
		System.out.println("Welcome to the Revature Reimbursement System"); 
		System.out.println("---------------------------------------------------------------"); 
		while (menuOptions) {
			System.out.println("PLEASE ENTR THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> Employee Portal");
			System.out.println("2 -> Finance Manager Portal");
			System.out.println("0 -> Exit Application");
			int firstChoice = promptSelection(1,2,0);
			
			switch(firstChoice) {
			case 1:
				handlePortal(Role.EMPLOYEE);
				break;
			case 2:
				handlePortal(Role.MANAGER);
				break;
			case 0:
				System.out.println("\nHave a great day! Goodbye.");
				menuOptions = false;
				break;
			}
		}//End of while loop
	}//End of display
	
	public void displayFinanceManagerMenu(User manager) {
		boolean managerPortal = true;
		
		System.out.println("---------------------------------------------------------------"); 
		System.out.println("Welcome to the Manager Portal, "+manager.getUserName()); 
		System.out.println("---------------------------------------------------------------"); 
		while (managerPortal) {
			System.out.println("PLEASE ENTR THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> View All Pending Reimbursements");
			System.out.println("2 -> View All Resolved Reimbursements");
			System.out.println("3 -> Process a Reimbursements");
			System.out.println("0 -> Return to Main Menu");
			int firstChoice = promptSelection(1,2,0);
			
			switch(firstChoice) {
			case 1:
				displayPendingReimbursements();
				break;
			case 2:
				displayResolvedReimbursements();
				break;
			case 3:
				processReimbursement(manager);
				break;
			case 0:
				System.out.println("Returning to Main Menu...");
				managerPortal = false;
				break;
			}
		}//End of while loop
	}//End of display
	
	private void displayEmployeeMenu(User employee) {
		boolean employeePortal = true;
		
		System.out.println("---------------------------------------------------------------"); 
		System.out.println("Welcome to the Employee Portal, "+employee.getUserName()); 
		System.out.println("---------------------------------------------------------------"); 
		while (employeePortal) {
			System.out.println("PLEASE ENTR THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> View Previous Requests");
			System.out.println("2 -> Submit Reimbursements");
			System.out.println("0 -> Return to Main Menu");
			int firstChoice = promptSelection(1,2,0);
			
			switch(firstChoice) {
			case 1:
				displayPreviousRequests(employee);
				break;
			case 2:
				submitReimbursement(employee);
				break;
			case 0:
				System.out.println("Returning to Main Menu...");
				employeePortal = false;
				break;
			}
		}//End of while loop
	}//End of display
	
	public void displayLoginMenu() {
		boolean login = true;
		System.out.println("---------------------------------------------------------------"); 
		System.out.println("Welcome to the Revature Reimbursement Login Portal"); 
		System.out.println("---------------------------------------------------------------"); 
		
		System.out.println("Please Login, or Choose to Register");
		System.out.println("1 -> Login");
		System.out.println("2 -> Register");
		System.out.println("0 -> Exit");
		do {
			int userChoice = promptSelection(1,2,0);
			switch(userChoice) {
			case 1:
				login = false;
				login();
				break;
			case 2:
				login = false;
				register();
				break;
			case 0:
				login = false;
				System.exit(0);
				break;
			}
		} while(login);
		
	}
	
	private void login() {
		authorService newAuth = new authorService();
		String
		username = "",
		password = "";
		boolean loop = true;
		while(loop) {
			boolean valid = false;
			System.out.println("Please enter your Username");
			//loop for a valid username
			while(!valid) {
				username = scan.nextLine().toString();
				if(!username.trim().equals("")) {
					valid = true;
					
				} else System.out.println("Invalid, please try again.");
			}
			valid = false;//reset boolean to false for next loop
			System.out.println("Please enter your Password");
			//loop for valid password
			while(!valid) {
				password = scan.nextLine().toString();
				if(!username.trim().equals("")) {
					valid = true;
				} else System.out.println("Invalid, please try again.");
			}
			//make sure the login is valid
			if (newAuth.login(username, password)) {
				User user = userService.getUserByUsername(username);
				handlePortal(user.getRole());
			}
		}
	}
	
	private void register() {
		User newUser = new User();
		while(true) {
			boolean valid = false;
			System.out.println("Please enter your Username");
			System.out.println("Hit esc to return");
			//loop for a valid username
			while(!valid) {
				newUser.setUserName(scan.nextLine().toString());
				if(!newUser.getUserName().trim().equals("")) {
					valid = true;
						
				} else System.out.println("Invalid, please try again.");
			}
			valid = false;//reset boolean to false for next loop
			System.out.println("Please enter your Password");
			//loop for valid password
			while(!valid) {
				newUser.setPassword(scan.nextLine().toString());
				if(!newUser.getPassword().trim().equals("")) {
					valid = true;
				} else System.out.println("Invalid, please try again.");
			}
			if(authorService.register(newUser)) {
				System.out.println("Congradulations! You've succesfully registered.");
				handlePortal(newUser.getRole());
			}
		}
	}
}
