package com.revature.service;

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

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.Offers;
import com.revature.models.Payment;
import com.revature.models.Sword;
import com.revature.repository.MenuImpl;
import com.revature.utilities.ConnectionFactory;
import com.revature.utilities.PasswordHashing;
import com.revature.utilities.ProxyApp;

public class EmployeeDaoImpl extends MenuImpl implements EmployeeDao{
	public final static Logger loggy = Logger.getLogger(ProxyApp.class);	
	boolean ordering = true;
	Scanner sc = new Scanner(System.in);

	@Override
	public void loginToAccount() {
		
			String username;
			String password;
			String choice;

				System.out.println("What is your username?");
				username = sc.next();
			
				System.out.println("What is your password?");
				password = sc.next();
		
				if(this.authenticateUser(username, password) == false) {
					loggy.info("Failed Employee login attempt.");
					System.out.println("Sorry, your credentials are incorrect. Enter yes to try again or no to log off.");
					choice = sc.next();
					switch(choice) {
					case "yes":
						this.loginToAccount();
						ordering = false;
						break;
					case "no":
						this.logOff();
					}
			}
	}

	@Override
	public Boolean authenticateUser(String username, String password) {
		
		Employee employee = new Employee(0,null,null,null,null,null);
				
		String sql = "select * from swords.employees where user_name = ? and password = ?;";
		
		PreparedStatement ps;
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, PasswordHashing.doHashing(password));
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				employee.setEmployeeNumber(rs.getInt("employee_number"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setTitle(rs.getString("title"));
				
				if(employee.getEmployeeNumber() > 0) {
					loggy.info("Employee Number: " + employee.getEmployeeNumber() + " has just logged in.");
					System.out.println("------------------------------------------------\n" +
					"                 Successful login\n"
							+ "------------------------------------------------");
					System.out.println("               Welcome Mr. " + employee.getLastName() + "!");
					loggy.info("Employee Number: " + employee.getEmployeeNumber() + " has just logged in.");
					this.employeeMenu(employee);
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Employee employeeMenu(Employee employee) {
		this.showEmployeeMenu();
		while(ordering == true) {
			String choice = sc.next();
			
		switch(choice) {
		case "1":
			this.showAllPendingOffers();
			this.employeeMenu(employee);
			ordering = false;
			break;
			
		case "2":
			this.showMaxOffersBySwordNumber();
			this.employeeMenu(employee);
			ordering = false;
			break;			
			
		case "3":
			this.acceptOffers(employee);
			this.employeeMenu(employee);
			ordering = false;
			break;
			
		case "4":
			this.rejectOffers(employee);
			this.employeeMenu(employee);
			ordering = false;
			break;
			
		case "5":
			this.addItems(employee);
			ordering = false;
			break;
			
		case "6":
			this.removeItems(employee);
			ordering = false;
			break;
			
		case "7":
			this.viewCustomerPayments(employee);
			this.employeeMenu(employee);
			ordering = false;
			break;
			
		case "8":
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
	public void logOff() {
		loggy.info("Employee logged off.");
		System.out.println("\n***********************************************************************");
		System.out.println("\n              />\r\n"
				+ " (           //------------------------------------------------------(\r\n"
				+ "(*)OXOXOXOXO(*>                  --------                             \\\r\n"
				+ " (           \\\\--------------------------------------------------------)\r\n"
				+ "              \\> \n");
		System.out.println("                 Thank you for choosing Swords Galore!");
		System.out.println("***********************************************************************");
		try {sc.close();
		
		} catch(IllegalStateException e) {
			System.out.println("");
			
		}
		
	}

	@Override
	public void showAllPendingOffers() {
		List<Offers> pendingOffers = new ArrayList<>();
		
		String sql = "select * from swords.offers where offer_pending = true;";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				pendingOffers.add(new Offers(
						rs.getInt("offer_number"),
						rs.getInt("sword_number"),
						rs.getInt("customer_number"),
						rs.getDouble("offer_amount")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Offers i : pendingOffers) {
			System.out.println(i);
		}
	}
	

	@Override
	public Employee acceptOffers(Employee employee) {
		System.out.println("Which offer_number do you want to approve?");
		int offerNumber = sc.nextInt();
		sc.nextLine();
		
		try {
		String sqlCheckOffer = "select offer_number from swords.offers where offer_number = ?";
		Connection conn = ConnectionFactory.getConnectionFromEnv();
		PreparedStatement ps5 = conn.prepareStatement(sqlCheckOffer);
		ps5.setInt(1, offerNumber);
		ResultSet rs = ps5.executeQuery();
		while(rs.next()) {
			offerNumber = rs.getInt("offer_number");
			if(offerNumber > 0) {
				String sqlApprove = "update swords.offers set offer_accepted = true, offer_pending = false, approved_by_employee_number = ? where offer_number = ?;";
				String sqlRejectOtherOffers = "delete from swords.offers where offer_pending = true and sword_number = (select sword_number from swords.offers where offer_number = ?);";
				String sqlCustomerInventory = "insert into swords.customer_inventory (name, type, sword_number, customer_number, winning_price) select si.name, si.\"type\" , so.sword_number , so.customer_number, so.offer_amount \r\n"
						+ "from swords.inventory si inner join swords.offers so on si.sword_number = so.sword_number where si.sword_number \r\n"
						+ "= (select sword_number from swords.offers where offer_number = ?);";
				String sqlChangeAvailability = "update swords.inventory set available = false where sword_number = (select sword_number from swords.offers where offer_number = ?);";
				
				
				try {
					PreparedStatement ps = conn.prepareStatement(sqlApprove);
					PreparedStatement ps2 = conn.prepareStatement(sqlRejectOtherOffers);
					PreparedStatement ps3 = conn.prepareStatement(sqlCustomerInventory);
					PreparedStatement ps4 = conn.prepareStatement(sqlChangeAvailability);
					
					ps.setInt(1, employee.getEmployeeNumber());
					ps.setInt(2, offerNumber);
					ps2.setInt(1, offerNumber);
					ps3.setInt(1, offerNumber);
					ps4.setInt(1, offerNumber);
					
					ps.execute();
					ps2.execute();
					ps3.execute();
					ps4.execute();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				loggy.info("Employee number: " + employee.getEmployeeNumber() + " has just approved offer number: " + offerNumber);
				System.out.println("Offer number: " + offerNumber + " has been approved by employee: " + employee.getEmployeeNumber());
				System.out.println("All pending offers for that sword have been deleted");
				
				FileWriter file;
				try {
					file = new FileWriter("C:\\Users\\Raymo\\git\\ray-campbell\\ProjectZero Documents\\Winning Bids.txt", true);
					PrintWriter output;
					output = new PrintWriter(file);
					output.append("\nOffer Number: " + offerNumber + " was approved by " + employee.getFirstName() + " " + employee.getLastName());
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Offer: " + offerNumber + " does not exist in the system.");
				this.employeeMenu(employee);
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return employee;
	}

	@Override
	public Employee rejectOffers(Employee employee) {
		System.out.println("Which offer_number do you want to reject?");
		int offerNumber = sc.nextInt();
		sc.nextLine();
		
		String sql = "delete from swords.offers where offer_number = ?;";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, offerNumber);
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loggy.info("Employee number: " + employee.getEmployeeNumber() + " has just deleted offer number: " + offerNumber);
		System.out.println("Offer number: " + offerNumber + " has been deleted from the system.");
		return employee;
	}

	@Override
	public Employee addItems(Employee employee) {
		
		this.showAddMenu();
		
		while(ordering==true) {
		Sword draft = new Sword(0,null,null,0,true);
		
		String type;
		String name;
		double offer;
		
		String choice = sc.next();
		
		switch(choice) {
		
		case "1":
			System.out.println("What type of sword are we adding today?");
			type = sc.next();
			draft.setType(type);
			
		case "3":
			System.out.println("What is the name of the sword we are adding today?");
			name = sc.next();
			draft.setName(name);
			
		case "4":
			System.out.println("What is the minimum offer we are accepting for this sword?");
			offer = sc.nextDouble();
			draft.setCurrent_offer(offer);
			if(offer<=0) {
				System.out.println("Please enter a valid offer. ");
				sc.nextLine();
				this.addItems(employee);
			} else {
				sc.nextLine();
			}
		case "5":
			System.out.println("Sword Type: " + draft.getType() + "\n" +
							   "Sword Name: " + draft.getName() + "\n" +
							   "Starting offer: " + draft.getCurrent_offer() + "\n" +
							   "Is all of this information correct? Enter yes or no. ");
			
			String confirmationChoice = sc.next();
			if(confirmationChoice.equals("yes")) {
				this.addSwordToSystem(employee, draft);
				ordering = false;
			} else {
				this.addItems(employee);
				ordering = false;
			} break;
		case "2":
			this.employeeMenu(employee);
			ordering = false;
			break;
		
		default:
			System.out.println("Please enter a valid number from the menu. ");
			this.addItems(employee);
		}
		}
		return employee;
	}

	@Override
	public Employee removeItems(Employee employee) {
		this.showRemoveMenu();
		while(ordering==true) {
			String choice = sc.next();
		switch(choice) {
		case "1":
			this.removeSwordFromSystem(employee);
			ordering = false;
			break;
			
		case "2":
			this.employeeMenu(employee);
			ordering = false;
			break;
		default:
			System.out.println("Please enter a valid number from the menu. ");
			this.addItems(employee);
		}
		}
		return employee;
	}

	@Override
	public Employee viewCustomerPayments(Employee employee) {
		List<Payment> allPayments = new ArrayList<>();
		
		String sql = "select * from swords.payments p ;";
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				allPayments.add(new Payment(
						rs.getInt("payment_number"),
						rs.getInt("sword_number"),
						rs.getDouble("total_balance"),
						rs.getDouble("weekly_payment"),
						rs.getInt("weekly_payments_left"),
						rs.getBoolean("paid_off")));
			}
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		allPayments.forEach(i -> System.out.println(i));
		return employee;
	}

	@Override
	public void showMaxOffersBySwordNumber() {
		List<Offers> pendingOffers = new ArrayList<>();
		
		String sql = "select offer_number, sword_number, customer_number, offer_amount from swords.offers o\r\n"
				+ "where offer_amount in (select max(offer_amount) from swords.offers o2 group by sword_number)\r\n"
				+ "and sword_number in (select sword_number from swords.offers o3 where offer_pending = true);";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				pendingOffers.add(new Offers(
						rs.getInt("offer_number"),
						rs.getInt("sword_number"),
						rs.getInt("customer_number"),
						rs.getDouble("offer_amount")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Offers i : pendingOffers) {
			System.out.println(i);
		}
	}

	@Override
	public Employee addSwordToSystem(Employee employee, Sword draft) {
		String sql = "insert into swords.inventory (type, name, current_offer, available)\r\n"
				+ "values(?,?,?, true);";
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, draft.getType());
			ps.setString(2, draft.getName());
			ps.setDouble(3, draft.getCurrent_offer());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		loggy.info("Employee number: " + employee.getEmployeeNumber() + " has just added the " + draft.getType() + ", " + draft.getName() + " to the system.");
		System.out.println("The " + draft.getType() + ": " + draft.getName() + " was successfully added to the system. ");
		this.employeeMenu(employee);
		return employee;
	}

	@Override
	public Employee removeSwordFromSystem(Employee employee) {
		System.out.println("What is the Sword Number that you wish to remove from the system?");
		int swordNumber = sc.nextInt();
		sc.nextLine();
		
		String sql = "delete from swords.offers where sword_number = ?;\r\n"
				+ "delete from swords.inventory where sword_number = ?;";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, swordNumber);
			ps.setInt(2, swordNumber);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loggy.info("Employee number: " + employee.getEmployeeNumber() + " has just removed sword number: " + swordNumber  + " from the system.");
		System.out.println("The Sword Number: " + swordNumber + " has been removed from the system.");
		this.employeeMenu(employee);
		return employee;
	}

		}


