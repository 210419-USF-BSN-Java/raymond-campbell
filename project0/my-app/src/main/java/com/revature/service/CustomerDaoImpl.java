package com.revature.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.models.Customer;
import com.revature.models.Offers;
import com.revature.models.Payment;
import com.revature.models.Sword;
import com.revature.repository.MenuImpl;
import com.revature.utilities.ConnectionFactory;
import com.revature.utilities.PasswordHashing;
import com.revature.utilities.ProxyApp;

public class CustomerDaoImpl extends MenuImpl implements CustomerDao{
	public final static Logger loggy = Logger.getLogger(ProxyApp.class);
	boolean ordering = true;
	Scanner sc = new Scanner(System.in);
	@Override
	public void createAccount(Customer draft) {
		
		String sql = "insert into swords.customers (user_name, password, first_name, last_name ) values (?,?,?,?);";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, draft.getUsername());
			ps.setString(2, draft.getPassword());
			ps.setString(3, draft.getFirstName());
			ps.setString(4, draft.getLastName());
			
			ps.execute();
			
		String sql2 = "select customer_number from swords.customers where user_name = ? and password = ?";
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		ps2.setString(1, draft.getUsername());
		ps2.setString(2, draft.getPassword());
		
		ResultSet rs = ps2.executeQuery();
		
		while(rs.next()) {
			draft.setCustomerNumber(rs.getInt("customer_number"));
		}
			
		} catch (SQLException e) {
			loggy.info("Failed account creation attempt.");
			System.out.println("Something went wrong with account creation.");
			e.printStackTrace();
		}
		loggy.info(draft.getUsername() + " has been added as a customer.");
		System.out.println("Your account has successfully been created, welcome to Swords Galore Mr. " + draft.getLastName());
		this.customerMenu(draft);
	}

	@Override
	public void loginToAccount() {
		
		while(ordering == true) {
			
			String username;
			String password;
			String choice;

				System.out.println("What is your username?");
				username = sc.next();
			
				System.out.println("What is your password?");
				password = sc.next();
		
				if(this.authenticateUser(username, password) == false) {
					loggy.info("Failed Customer login attempt.");
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
		}

	@Override
	public boolean authenticateUser(String username, String password) {
		
		Customer customer = new Customer(0,null,null,null,null);
		
		int customerId;
		String firstName;
		String lastName;
		
		String sql = "select * from swords.customers where user_name = ? and password = ?;";
		
		PreparedStatement ps;
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, PasswordHashing.doHashing(password));
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				customerId = rs.getInt(1);
				firstName = rs.getString("first_name");
				lastName = rs.getString("last_name");
				
				customer.setCustomerNumber(customerId);
				customer.setUsername(username);
				customer.setPassword(password);
				customer.setFirstName(firstName);
				customer.setLastName(lastName);
				
				if(customer.getCustomerNumber() > 0) {
					System.out.println("------------------------------------------------\n" +
					"                 Successful login\n"
							+ "------------------------------------------------");
					System.out.println("               Welcome Mr. " + customer.getLastName() + "!");
					loggy.info(customer.getUsername() + " has logged in.");
					this.customerMenu(customer);
					return true;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public Customer customerMenu(Customer customer) {
		this.showCustomerMenu();
		
		while(ordering == true) {
			String choice = sc.next();
			
		switch(choice) {
		case "1":
			this.auctionAccount(customer);
			ordering = false;
			break;
		
		case "2":
			this.viewOwnedSwords(customer);
			ordering = false;
			break;
			
		case "3":
			this.paymentsAccount(customer);
			ordering = false;
			break;
			
		case "4":
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
	public Customer auctionAccount(Customer customer) {
		this.showAuctionMenu();
		this.auctionAccountInput(customer);
		return customer;
	}
	
	@Override
	public Customer auctionAccountInput(Customer customer) {
		while(ordering == true) {
			
			String choice = sc.next();
			
			switch(choice) {
			case "1":
				System.out.println("Available Inventory: \n");
				this.showAllAuctionSwords();
				this.auctionAccount(customer);
				ordering = false;
				break;
				
			case "2":
				System.out.println("Available Inventory: \n");
				this.showSwordsByType();
				this.auctionAccount(customer);
				ordering = false;
				break;
				
			case "3":
				System.out.println("Pending offers you have made to available swords: \n");
				this.showPendingOffers(customer);
				this.auctionAccount(customer);
				ordering = false;
				break;
				
			case "4":
				this.bidOnSwords(customer);
				ordering = false;
				break;
				
			case "5":
				this.showWinningBids(customer);
				ordering = false;
				break;
				
			case "6":
				this.customerMenu(customer);
				ordering = false;
				break;
			
			default:
				System.out.println("Please enter a valid number from the menu.");			
			
			}
		}
		return customer;
	}
	
	@Override
	public void showAllAuctionSwords() {
		List<Sword> availableSwords = new ArrayList<>();
		
		String sql = "select * from swords.inventory where available = true;";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				availableSwords.add(new Sword(
						rs.getInt("sword_number"),
						rs.getString("type"),
						rs.getString("name"),
						rs.getDouble("current_offer")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Sword i : availableSwords) {
			System.out.println(i);
		}
	}
	
	@Override
	public void showSwordsByType(){
		List<Sword> availableSwords = new ArrayList<>();
		
		String sql = "select * from swords.inventory where available = true order by type;";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				availableSwords.add(new Sword(
						rs.getInt("sword_number"),
						rs.getString("type"),
						rs.getString("name"),
						rs.getDouble("current_offer")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Sword i : availableSwords) {
			System.out.println(i);
		}
	}
	
	@Override
	public Customer bidOnSwords(Customer customer) {
		
		Sword sword = new Sword(0,null,null,0);
		Double bid;
		int offerNumber;
			
		try {
			System.out.println("Please enter the sword number of the sword that you want to bid on: ");
		    int swordNumber = sc.nextInt();
		    sc.nextLine();
	
		
		    String sql = "select * from swords.inventory where sword_number = ? and available = true;";
		    String sql2 = "insert into swords.offers (sword_number, customer_number, offer_amount) values (?,?,?);";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, swordNumber);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				sword.setSwordNumber(swordNumber);
				sword.setType(rs.getString("type"));
				sword.setName(rs.getString("name"));
				sword.setCurrent_offer(rs.getDouble("current_offer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} if(sword.getName() == null) {
			System.out.println("Please select a valid sword number that is available from our list.");
			this.auctionAccount(customer);
		} else {
			System.out.println(sword);
			System.out.println("Please enter a bid higher than the current price. Or enter 0 to go back to the Auction menu");
			bid = sc.nextDouble();
			sc.nextLine();
			if(bid > sword.getCurrent_offer()) {
				Connection conn = ConnectionFactory.getConnectionFromEnv();
				sql = "update swords.inventory set current_offer = ? where sword_number = ?;";
				String sql3 = "select max(offer_number) from swords.offers o ;";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				
				ps.setDouble(1, bid);
				ps.setInt(2, swordNumber);
				ps.execute();
				
				ps2.setInt(1, swordNumber);
				ps2.setInt(2, customer.getCustomerNumber());
				ps2.setDouble(3, bid);
				ps2.execute();
				
				ResultSet rs = ps3.executeQuery();
				
				while(rs.next()) {
					offerNumber = rs.getInt(1);
					FileWriter file;
					try {
						file = new FileWriter("C:\\Users\\Raymo\\git\\ray-campbell\\ProjectZero Documents\\Offer history.txt", true);
						PrintWriter output;
						output = new PrintWriter(file);
						output.append("\nOffer Number: " + offerNumber + " of the amount $" + bid + "0 was posted by Customer Number: " + customer.getCustomerNumber()
						+ " on Sword Number:" + swordNumber);
						output.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				loggy.info("A bid has been successfully posted on sword number: " + swordNumber);
				System.out.println("A bid of : $" + bid + "0 has been successfully posted.");
				
				this.auctionAccount(customer);
				ordering = false;
			}
			else if(bid == 0) {
				this.auctionAccount(customer);
				ordering = false;
			}
			else {
				loggy.info("An invalid bid of " + bid + " has been entered.");
				System.out.println("Please enter a valid bid. \n");
				this.bidOnSwords(customer);
			}
		}
		} catch (InputMismatchException e) {
			System.out.println("Please enter a valid number. \n");
			sc.nextLine();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
		
		}

	@Override
	public Customer showPayments(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logOff() {
		loggy.info("a user has just logged off.");
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
	public Customer showPendingOffers(Customer customer){
		
		List<Offers> pendingOffers = new ArrayList<>();
		
		String sql = "select * from swords.offers where customer_number = ? and offer_pending = true;";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customer.getCustomerNumber());
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
		return customer;
	}

	@Override
	public Customer showWinningBids(Customer customer) {
		
		List<Sword> winningSwords = new ArrayList<>();
		
		String sql = "select * from swords.customer_inventory ci where finance_setup = false and customer_number = ?;";
		
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, customer.getCustomerNumber());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				winningSwords.add(new Sword(
						rs.getInt("sword_number"),
						rs.getString("type"),
						rs.getString("name"),
						rs.getDouble("winning_price")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Sword i : winningSwords) {
			System.out.println(i);
		}	
		if(winningSwords.size() <= 0) {
			System.out.println("There are currently no winning bids from you at this time.");
			this.auctionAccount(customer);
		} else {
		System.out.println("Congratulations on winning these bids! You will now be taken to our finance page.");
		this.finance(customer, winningSwords);
		}
		return customer;
		}

	@Override
	public Customer finance(Customer customer, List<Sword> winningSwords) {
		for(Sword i : winningSwords) {
			System.out.println("How would you like to pay for the sword: " + i.getName() +"?");
			System.out.println("------------------------------------------------");
			System.out.println("               Total Price: $" + i.getCurrent_offer() + "0");
			System.out.println("------------------------------------------------");
			while(ordering == true) {
				
				this.showFinanceMenu();
				String choice = sc.next();
				
				switch(choice) {
				case "1":
					this.payOffWhole(customer, i.getSwordNumber());
					ordering = false;
					break;
					
				case "2":
					this.payOneYear(customer, i.getSwordNumber(), i.getCurrent_offer());
					ordering = false;
					break;
					
				case "3":
					this.payTwoYear(customer, i.getSwordNumber(), i.getCurrent_offer());
					ordering = false;
					break;

				default:
					System.out.println("Please enter a valid number from the menu.");			
				}
			}
		}
		return customer;
	}

	@Override
	public Customer payOffWhole(Customer customer, int swordId) {
		String sqlUpdatePaid = "update swords.customer_inventory set paid_off = true where sword_number = ?;";
		String sqlUpdateFinance = "update swords.customer_inventory set finance_setup = true where sword_number = ?;";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sqlUpdatePaid);
			ps.setInt(1, swordId);
			ps.execute();
			
			PreparedStatement ps2 = conn.prepareStatement(sqlUpdateFinance);
			ps2.setInt(1, swordId);
			ps2.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		loggy.info("Sword id: " + swordId + " has been fully paid off.");
		System.out.println("Your payment has been processed. This sword is fully paid off.");
		this.auctionAccount(customer);
		return customer;
		
	}

	@Override
	public Customer payOneYear(Customer customer, int swordId, double swordAmount) {

		Double weeklyPayment = swordAmount/52;
		String sqlAddPaymentPlan = "insert into swords.payments (sword_number,customer_number,total_balance, weekly_payment, weekly_payments_left)\r\n"
				+ "select sword_number , customer_number, offer_amount, offer_amount/52, 52 from swords.offers where sword_number = ?;";
		String sqlUpdateFinance = "update swords.customer_inventory set finance_setup = true where sword_number = ?;";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sqlUpdateFinance);
			ps.setInt(1, swordId);
			ps.execute();
			
			PreparedStatement ps2 = conn.prepareStatement(sqlAddPaymentPlan);
			ps2.setInt(1, swordId);
			ps2.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loggy.info("A one year payment plan has just been set up for sword number: " + swordId + " with customer number: " + customer.getCustomerNumber());
		System.out.println("Your payment plan has been successfully been added to the system. Your weekly payment is $" + new DecimalFormat("#.##").format(weeklyPayment));
		this.auctionAccount(customer);
		return customer;
	}

	@Override
	public Customer payTwoYear(Customer customer, int swordId, double swordAmount) {

		Double weeklyPayment = swordAmount/104;
		String sqlAddPaymentPlan = "insert into swords.payments (sword_number,customer_number,total_balance, weekly_payment, weekly_payments_left)\r\n"
				+ "select sword_number , customer_number, offer_amount, offer_amount/104, 104 from swords.offers where sword_number = ?;";
		String sqlUpdateFinance = "update swords.customer_inventory set finance_setup = true where sword_number = ?;";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sqlUpdateFinance);
			ps.setInt(1, swordId);
			ps.execute();
			
			PreparedStatement ps2 = conn.prepareStatement(sqlAddPaymentPlan);
			ps2.setInt(1, swordId);
			ps2.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loggy.info("A two year payment plan has just been set up for sword number: " + swordId + " with customer number: " + customer.getCustomerNumber());
		System.out.println("Your payment plan has been successfully been added to the system. Your weekly payment is $" + new DecimalFormat("#.##").format(weeklyPayment));
		this.auctionAccount(customer);
		return customer;
	}

	@Override
	public Customer viewOwnedSwords(Customer customer) {
		List<Sword> ownedSwords = new ArrayList<>();
		
		String sql = "select * from swords.customer_inventory ci where finance_setup = true and customer_number = ?;";
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, customer.getCustomerNumber());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ownedSwords.add(new Sword(
						rs.getInt("sword_number"),
						rs.getString("type"),
						rs.getString("name")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Sword i : ownedSwords) {
			System.out.println("Sword Number: " + i.getSwordNumber());
			System.out.println("Sword Name: " + i.getName());
			System.out.println("Sword Type: " + i.getType());
		}
		if(ownedSwords.isEmpty()) {
			System.out.println("Sorry Mr. " + customer.getLastName() + ", you currently do not own any swords at this time.");
		}
		
		this.customerMenu(customer);
		return customer;
	}

	@Override
	public Customer paymentsAccount(Customer customer) {
		this.showPaymentsMenu();
		this.paymentsAccountInput(customer);
		return customer;
	}

	@Override
	public Customer paymentsAccountInput(Customer customer) {
		while(ordering == true) {
			
			String choice = sc.next();
			
			switch(choice) {
			case "1":
				System.out.println("Payments Due: \n");
				this.showAllPayments(customer);
				System.out.println("------------------------------------------------------------");
				this.paymentsAccount(customer);
				ordering = false;
				break;
				
			case "2":
				this.submitPayment(customer);
				ordering = false;
				break;
				
			case "3":
				this.customerMenu(customer);
				ordering = false;
				break;

			default:
				System.out.println("Please enter a valid number from the menu.");			
			
			}
		}
		return customer;
	}

	@Override
	public Customer showAllPayments(Customer customer) {
		List<Payment> availablePayments = new ArrayList<>();
		
		String sql = "select * from swords.payments p where customer_number = ?;";
		
		try {
			Connection conn = ConnectionFactory.getConnectionFromEnv();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, customer.getCustomerNumber());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				availablePayments.add(new Payment(
						rs.getInt("payment_number"),
						rs.getInt("sword_number"),
						rs.getDouble("total_balance"),
						rs.getDouble("weekly_payment"),
						rs.getInt("weekly_payments_left"),
						rs.getBoolean("paid_off")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Payment i : availablePayments) {
			System.out.println(i);
		}
		return customer;
	}

	@Override
	public Customer submitPayment(Customer customer) {
		while(ordering==true) {
		System.out.println("Enter the payment number of the sword which you'd like to make a payment on: ");
		int paymentNumber = sc.nextInt();
		sc.nextLine();
		System.out.println("How many weeks would you like to pay off today?");
		int weeks = sc.nextInt();
		sc.nextLine();
		
		System.out.println("You have chosen " + weeks + " weeks to pay off for payment number: " + paymentNumber + ". \n"
				+ "If this is correct enter yes. Enter no if incorrect. Enter out to go back to customer menu.");
		String choice = sc.nextLine();
		switch(choice) {
		case "yes":
			String sql = "update swords.payments set total_balance = total_balance - (weekly_payment * ?) where payment_number = ?;";
			String sql2 = "update swords.payments set weekly_payments_left = (weekly_payments_left - ?) where payment_number = ?;";
			try {
				Connection conn = ConnectionFactory.getConnectionFromEnv();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, weeks);
				ps.setInt(2, paymentNumber);
				ps.execute();
				
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setInt(1, weeks);
				ps2.setInt(2, paymentNumber);
				ps2.execute();
				loggy.info("Customer number: " + customer.getCustomerNumber() + " has just paid " + paymentNumber + " weeks off their remaining balance.");
				System.out.println("Your payment has been processed.");
				this.paymentsAccount(customer);
				ordering = false;
				break;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		case "no":
			this.showAllPayments(customer);
			this.submitPayment(customer);
			ordering = false;
			break;
			
		case "out":
			this.customerMenu(customer);
			ordering = false;
			break;
		default:
			System.out.println("Please enter a valid input");
		}
		
		}
		return customer;
	}

}

