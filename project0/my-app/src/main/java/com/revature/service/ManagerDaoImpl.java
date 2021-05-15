package com.revature.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.utilities.ConnectionFactory;
import com.revature.utilities.PasswordHashing;


public class ManagerDaoImpl extends EmployeeDaoImpl implements ManagerDao{
	
	@Override
	public Manager addEmployee(Manager manager) {
		while(ordering==true) {
		Employee draft = new Employee(0, null, null, null, null, null);
		
		String username;
		String password;
		String firstName;
		String lastName;
		
		String choice = sc.nextLine();
		
		switch(choice) {
		
		case "1":
			System.out.println("Enter what username the new employee is going to have: ");
			username = sc.nextLine();
			draft.setUsername(username);
			
		case "3":
			System.out.println("Enter the temporary password for the new employee: ");
			password = sc.nextLine();
			draft.setPassword(password);
			
		case "4":
			System.out.println("Enter the new employee's first name: ");
			firstName = sc.nextLine();
			draft.setFirstName(firstName);
			
		case "5":
			System.out.println("Enter the new employee's last name: ");
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
				this.createAccount(manager, draft);
				ordering = false;
			}else {
				this.managerMenu(manager);
			} break;
		case "2":
			this.managerMenu(manager);
			break;
		}
		}
		
		return manager;
}
		

	@Override
	public Manager fireEmployee(Manager manager) {
		while(ordering==true) {
		Employee fired = new Employee(0, null, null, null, null, null);
		String reason;	
		
		String choice = sc.nextLine();
		
		switch(choice) {
		
		case "1":
			System.out.println("What is the employee number of the employee we are removing today?");
			fired.setEmployeeNumber(sc.nextInt());
			sc.nextLine();
			
		case "3":
			System.out.println("Please enter a concise explanation for firing this employee: ");
			reason = sc.nextLine();
			
			String sql = "select * from swords.employees where employee_number = ?;";
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, fired.getEmployeeNumber());
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					fired.setFirstName(rs.getString("first_name"));
					fired.setLastName(rs.getString("last_name"));
				}
				
				System.out.println("Employee Number: " + fired.getEmployeeNumber() + "\n" +
						"First Name: " + fired.getFirstName() + "\n" +
						"Last Name: " + fired.getLastName() + "\n" +
						"Reason: " + reason + "\n" +
						"Is all of this information correct? Enter yes or no. ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String confirmationChoice = sc.next();
			if(confirmationChoice.equals("yes")) {
				String sql2 = "Delete from swords.employees where employee_number = ?;";
				PreparedStatement ps2;
				try {
					ps2 = conn.prepareStatement(sql2);
					ps2.setInt(1, fired.getEmployeeNumber());
					ps2.executeUpdate();
					FileWriter file;
					PrintWriter output;
					file = new FileWriter("C:\\Users\\Raymo\\git\\ray-campbell\\ProjectZero Documents\\Fired Employees.txt", true);
					output = new PrintWriter(file);
					output.append("\nEmployee Number: " + fired.getEmployeeNumber() + " was terminated by Manager Number: " + manager.getEmployeeNumber() + 
							"\n for the reason: " + reason);
					output.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.managerMenu(manager);
				ordering = false;
				break;
			}else {
				this.managerMenu(manager);
				ordering = false;
			} break;
		case "2":
			this.managerMenu(manager);
			ordering = false;
			break;
		}
		}
		return manager;
		
	}

	@Override
	public void viewAllOffers() {
		File file = new File("C:\\Users\\Raymo\\git\\ray-campbell\\ProjectZero Documents\\Offer history.txt");
		System.out.println("------------------------------------------------\n" +
		"                 Offer History\n"
				+ "------------------------------------------------");
		try {
			Scanner fs = new Scanner(file);
			while(fs.hasNextLine()) {
				System.out.println(fs.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Manager createAccount(Manager manager, Employee draft) {
		String sql = "insert into swords.employees (user_name, password, first_name, last_name ) values (?,?,?,?);";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, draft.getUsername());
			ps.setString(2, draft.getPassword());
			ps.setString(3, draft.getFirstName());
			ps.setString(4, draft.getLastName());
			
			ps.execute();
			
		String sql2 = "select employee_number from swords.employees where user_name = ? and password = ?";
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		ps2.setString(1, draft.getUsername());
		ps2.setString(2, draft.getPassword());
		
		ResultSet rs = ps2.executeQuery();
		
		while(rs.next()) {
			draft.setEmployeeNumber(rs.getInt("employee_number"));
		}
			
		} catch (SQLException e) {
			loggy.info("Failed account creation attempt.");
			System.out.println("Something went wrong with account creation.");
			e.printStackTrace();
		}
		loggy.info(draft.getUsername() + " has been added as an employee.");
		System.out.println("The new employee account with employee number: " + draft.getEmployeeNumber() + " has successfully been created.");
		this.managerMenu(manager);
		return manager;
	}

	@Override
	public Manager managerMenu(Manager manager) {
		System.out.println("------------------------------------------------\n" +
		"                 Manager Menu\n"
				+ "------------------------------------------------");
		this.showManagerMenu();
		while(ordering == true) {
			String choice = sc.next();
			
		switch(choice) {
		case "1":
			this.employeeMenu(manager);
			ordering = false;
			break;
			
		case "2":
			this.showAddMenu();
			this.addEmployee(manager);
			ordering = false;
			break;			
			
		case "3":
			this.showRemoveMenu();
			this.fireEmployee(manager);
			ordering = false;
			break;
			
		case "4":
			this.viewAllOffers();
			this.managerMenu(manager);
			ordering = false;
			break;
			
		case "5":
			this.viewWinningBids();
			this.managerMenu(manager);
			ordering = false;
			break;

		case "6":
			this.viewAllEmployees();
			this.managerMenu(manager);
			ordering = false;
			break;
			
		case "7":
			this.logOff();
			ordering = false;
			break;
			
		default:
			System.out.println("Please enter a valid number from the menu.");
		}
		}
		return null;
	}

	@Override
	public Boolean authenticateUser(String username, String password) {
		
		Manager manager = new Manager(0,null,null,null,null,null);
				
		String sql = "select * from swords.employees where user_name = ? and password = ?;";
		
		PreparedStatement ps;
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, PasswordHashing.doHashing(password));
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				manager.setEmployeeNumber(rs.getInt("employee_number"));
				manager.setFirstName(rs.getString("first_name"));
				manager.setLastName(rs.getString("last_name"));
				manager.setTitle(rs.getString("title"));
				
				if(manager.getEmployeeNumber() > 0 && manager.getTitle().equals("Manager")) {
					loggy.info("Manager Number: " + manager.getEmployeeNumber() + " has just logged in.");
					System.out.println("------------------------------------------------\n" +
					"                 Successful login\n"
							+ "------------------------------------------------");
					System.out.println("               Welcome Mr. " + manager.getLastName() + "!");
					loggy.info("Employee Number: " + manager.getEmployeeNumber() + " has just logged in.");
					this.managerMenu(manager);
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void viewWinningBids() {
		File file = new File("C:\\Users\\Raymo\\git\\ray-campbell\\ProjectZero Documents\\Winning Bids.txt");
		System.out.println("------------------------------------------------\n" +
		"                 Winning Bids\n"
				+ "------------------------------------------------");
		try {
			Scanner fs = new Scanner(file);
			while(fs.hasNextLine()) {
				System.out.println(fs.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void viewAllEmployees() {
		List<Employee> empList = new ArrayList<>();
		String sql = "select * from swords.employees where title = ?;";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Associate");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				empList.add(new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
						));
			}
			empList.forEach(i -> System.out.println(i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
			}
		

	}
