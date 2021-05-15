package com.revature.service;

import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.repository.MenuImpl;
import com.revature.utilities.PasswordHashing;

public class FrontImpl extends MenuImpl implements Front {
	
	CustomerDao customer = new CustomerDaoImpl();
	EmployeeDao employee = new EmployeeDaoImpl();
	ManagerDaoImpl manager = new ManagerDaoImpl();
	Scanner sc = new Scanner(System.in);
	boolean ordering = true;

	@Override
	public void greetUser() {
		//art credit is from: �ASCII Art Swords - Asciiart.Eu.�
		System.out.println("      O                                     O\r\n"
				+ "{o)xxx|===============-  *  -===============|xxx(o}\r\n"
				+ "      O                                     O");
		System.out.println("             Welcome to Swords Galore!    ");
		System.out.println("      O                                     O\r\n"
				+ "{o)xxx|===============-  *  -===============|xxx(o}\r\n"
				+ "      O                                     O");
		this.showMainMenu();
		this.mainMenuInput();
	}
	
	@Override
	public void mainMenuInput() {
		
		while(ordering == true) {
			
			String choice = sc.nextLine(); //user input
			
			switch(choice) {
			case "1": 
				this.showLoginMenu();
				this.loginInput();
				ordering = false;
				break;
			
			case "2":
				this.showRegisterMenu();
				this.registerInput();
				ordering = false;
				break;
			
			case "3":
				this.showEmployeeLoginMenu();
				this.employeLoginInput();
				ordering = false;
				break;			
				
			case "4":
				manager.loginToAccount();
				ordering = false;
				break;			
				
			case "5":
				this.logOut();
				ordering = false;
				break;
			
			default:
				System.out.println("Please enter a valid number from the menu.");
				System.out.println("------------------------------------------------");
				this.greetUser();
			}
		}


		
	}
	
	@Override
	public void logOut() {
		//art credit is from: �ASCII Art Swords - Asciiart.Eu.�
		System.out.println("\n***********************************************************************");
		System.out.println("\n              />\r\n"
				+ " (           //------------------------------------------------------(\r\n"
				+ "(*)OXOXOXOXO(*>                  --------                             \\\r\n"
				+ " (           \\\\--------------------------------------------------------)\r\n"
				+ "              \\> \n");
		System.out.println("                 Thank you for choosing Swords Galore!");
		System.out.println("***********************************************************************");
		sc.close();
	}
	
	@Override
	public void loginInput() {
		while(ordering == true) {
			
			String choice = sc.nextLine();
			
			switch(choice) {
			case "1": 
				customer.loginToAccount();
				ordering = false;
				break;
			
			case "2":
				this.greetUser();
				ordering = false;
				break;

			default:
				System.out.println("Please enter a valid number from the menu.");
				System.out.println("------------------------------------------------");
			}
		
	}

}

	@Override
	public void registerInput() {
		while(ordering == true) {
			
			String choice = sc.nextLine(); //user input
			
			switch(choice) {
			case "1":
				this.registrationMenu();
				this.registration();
				ordering = false;
				break;
			
			case "2":
				this.greetUser();
				ordering = false;
				break;

			default:
				System.out.println("Please enter a valid number from the menu.");
				System.out.println("------------------------------------------------");
				this.showRegisterMenu();
			}
		
	}


}

	@Override
	public void registration() {
		while(ordering==true) {
		
		Customer draft = new Customer(0, null, null, null, null);
		
		String username;
		String password;
		String firstName;
		String lastName;
		
		String choice = sc.nextLine();
		
		switch(choice) {
		
		case "1":
			System.out.println("Enter what username you would like to have: ");
			username = sc.nextLine();
			draft.setUsername(username);
			
		case "3":
			System.out.println("Enter a password: ");
			password = sc.nextLine();
			draft.setPassword(password);
			
		case "4":
			System.out.println("Enter your first name: ");
			firstName = sc.nextLine();
			draft.setFirstName(firstName);
			
		case "5":
			System.out.println("Enter your last name: ");
			lastName = sc.nextLine();
			draft.setLastName(lastName);
			
		case "6":
			System.out.println("Username: " + draft.getUsername() + "\n" +
					"Password: " + draft.getPassword() + "\n" +
					"First Name: " + draft.getFirstName() + "\n" +
					"Last Name: " + draft.getLastName() + "\n" +
					"Is all of this information correct? Enter yes or no. ");
			
			String confirmationChoice = sc.next();
			if(confirmationChoice.equals("yes")) {
				draft.setPassword(PasswordHashing.doHashing(draft.getPassword()));
				customer.createAccount(draft);
				ordering = false;
			}else {
				this.registration();
			} break;
		case "2":
			this.greetUser();
			break;
		}
		}
		
	}

	@Override
	public void employeLoginInput(){
		while(ordering == true) {
			
			String choice = sc.nextLine();
			
			switch(choice) {
			case "1": 
				employee.loginToAccount();
				ordering = false;
				break;
			
			case "2":
				this.greetUser();
				ordering = false;
				break;

			default:
				System.out.println("Please enter a valid number from the menu.");
				System.out.println("------------------------------------------------");
			}
		
	}

}

}
