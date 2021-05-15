package com.revature.repository;

import java.util.Map;
import java.util.TreeMap;

import com.revature.models.Feature;


public class MenuImpl implements Menu{

	@Override
	public void showMainMenu() {
		
		Map<Integer, Feature> mainFeatures = new TreeMap<>();
		
		Feature login = new Feature("login to your customer account.");
		Feature register = new Feature("register for an account.");
		Feature employee = new Feature("login as an employee.");
		Feature manager = new Feature("login as a manager.");
		Feature logout = new Feature("log off.");
		
		mainFeatures.put(1, login);
		mainFeatures.put(2, register);
		mainFeatures.put(3, employee);
		mainFeatures.put(4, manager);
		mainFeatures.put(5, logout);
		
		for (Integer I : mainFeatures.keySet()) {
			System.out.println("      Enter " + I + " to " + mainFeatures.get(I).choiceName);
	}
}

	@Override
	public void showLoginMenu() {
		
		Map<Integer, Feature> loginFeatures = new TreeMap<>();
		
		Feature login = new Feature("to go to the login portal.");
		Feature mainMenu = new Feature("return to the main menu.");
		
		loginFeatures.put(1, login);
		loginFeatures.put(2, mainMenu);
		
		for (Integer I : loginFeatures.keySet()) {
			System.out.println("      Enter " + I + " to " + loginFeatures.get(I).choiceName);
	}
}

	@Override
	public void showRegisterMenu() {
		
		Map<Integer, Feature> registerFeatures = new TreeMap<>();
		
		Feature login = new Feature("to register for an account.");
		Feature mainMenu = new Feature("return to the main menu.");
		
		registerFeatures.put(1, login);
		registerFeatures.put(2, mainMenu);
		
		for (Integer I : registerFeatures.keySet()) {
			System.out.println("      Enter " + I + " to " + registerFeatures.get(I).choiceName);
	}
}
	
	@Override
	public void registrationMenu() {
		
		Map<Integer, Feature> registeration = new TreeMap<>();

		Feature enterInformation = new Feature("enter your information. ");
		Feature mainMenu = new Feature("go back to the main menu.");
		
		registeration.put(1, enterInformation);
		registeration.put(2, mainMenu);
		
		for(Integer I: registeration.keySet()) {
			System.out.println("Enter " + I + " to " + registeration.get(I).choiceName);
		}		
	}

	@Override
	public void showEmployeeLoginMenu() {
		
		Map<Integer, Feature> employeeFeatures = new TreeMap<>();
		
		Feature login = new Feature("to go to the employee portal.");
		Feature mainMenu = new Feature("return to the main menu.");
		
		employeeFeatures.put(1, login);
		employeeFeatures.put(2, mainMenu);
		
		for (Integer I : employeeFeatures.keySet()) {
			System.out.println("      Enter " + I + " to " + employeeFeatures.get(I).choiceName);
	}
}
	@Override
	public void showCustomerMenu(){
	
		Map<Integer, Feature> custAccount = new TreeMap<>();
		
		Feature  viewAvailableSwords = new Feature("to view swords for auction");
		Feature viewOwnedSwords = new Feature("to view sword collection");
		Feature viewPayments = new Feature("to view account financing");
		Feature logout = new Feature("to log off");
		
		custAccount.put(1, viewAvailableSwords);
		custAccount.put(2, viewOwnedSwords);
		custAccount.put(3, viewPayments);
		custAccount.put(4, logout);
		
		
		//art credit is from: �ASCII Art Swords - Asciiart.Eu.�
		System.out.println("         />_________________________________\r\n"
				+ "[########[]_________________________________>\r\n"
				+ "         \\>");
		System.out.println("-----------------Customer Menu------------------");
		for(Integer I: custAccount.keySet()) {
			System.out.println("Enter " + I + " to " + custAccount.get(I).choiceName);
		}
	}

	@Override
	public void showAuctionMenu() {
		
		Map<Integer, Feature> auctionMenu = new TreeMap<>();
		
		Feature viewAllSwords = new Feature("view all available swords.");
		Feature viewSwordsByType = new Feature("view swords by type");
		Feature viewSwordsByOffer = new Feature("view available swords that you have made an offer on");
		Feature makeOffer = new Feature("make an offer for a sword");
		Feature winningBids = new Feature("check winning bids");
		Feature customerMenu = new Feature("go back to customer menu");
		
		auctionMenu.put(1, viewAllSwords);
		auctionMenu.put(2, viewSwordsByType);
		auctionMenu.put(3, viewSwordsByOffer);
		auctionMenu.put(4, makeOffer);
		auctionMenu.put(5, winningBids);
		auctionMenu.put(6, customerMenu);
		

		System.out.println("-----------------------Auction Menu-------------------------");
		for(Integer I: auctionMenu.keySet()) {
			System.out.println("Enter " + I + " to " + auctionMenu.get(I).choiceName);
		}
		
	}

	@Override
	public void showEmployeeMenu(){
	
		Map<Integer, Feature> employeeAccount = new TreeMap<>();
		
		Feature  viewPendingOffers = new Feature("view pending offers for merchandise");
		Feature viewMaxOffers = new Feature("view highest offers by sword number");
		Feature acceptOffers = new Feature("accept offers");
		Feature rejectOffers = new Feature("reject offers");
		Feature addItems = new Feature("add items to auction inventory");
		Feature removeItems = new Feature("remove items from auction inventory ");
		Feature viewPayments = new Feature("view customer payments");
		Feature logout = new Feature("to log off");
		
		employeeAccount.put(1, viewPendingOffers);
		employeeAccount.put(2, viewMaxOffers);
		employeeAccount.put(3, acceptOffers);
		employeeAccount.put(4, rejectOffers);
		employeeAccount.put(5, addItems);
		employeeAccount.put(6, removeItems);
		employeeAccount.put(7, viewPayments);
		employeeAccount.put(8, logout);

		//art credit is from: �ASCII Art Swords - Asciiart.Eu.�
		System.out.println("            /\\\r\n"
				+ "/vvvvvvvvvvvv \\--------------------------------------,\r\n"
				+ "`^^^^^^^^^^^^ /=====================================\"\r\n"
				+ "            \\/");
		System.out.println("-----------------Employee Menu------------------");
		for(Integer I: employeeAccount.keySet()) {
			System.out.println("Enter " + I + " to " + employeeAccount.get(I).choiceName);
		}
	}

	@Override
	public void showAddMenu() {
		
		Map<Integer, Feature> addFeatures = new TreeMap<>();
		
		Feature login = new Feature("to go to the add portal.");
		Feature mainMenu = new Feature("return to the employee menu.");
		
		addFeatures.put(1, login);
		addFeatures.put(2, mainMenu);
		
		for (Integer I : addFeatures.keySet()) {
			System.out.println("      Enter " + I + " to " + addFeatures.get(I).choiceName);
	}
}

	@Override
	public void showRemoveMenu() {
		
		Map<Integer, Feature> removeFeatures = new TreeMap<>();
		
		Feature login = new Feature("to go to the remove portal.");
		Feature mainMenu = new Feature("return to the employee menu.");
		
		removeFeatures.put(1, login);
		removeFeatures.put(2, mainMenu);
		
		for (Integer I : removeFeatures.keySet()) {
			System.out.println("      Enter " + I + " to " + removeFeatures.get(I).choiceName);
	}
}

	@Override
	public void showFinanceMenu() {
		
		Map<Integer, Feature> financeFeatures = new TreeMap<>();
		
		Feature payAll = new Feature("to pay the entire balance up front.");
		Feature oneYearFinance = new Feature("to finance for one year.");
		Feature twoYearFinance = new Feature("to finance for two years.");
		
		financeFeatures.put(1, payAll);
		financeFeatures.put(2, oneYearFinance);
		financeFeatures.put(3, twoYearFinance);
		
		for (Integer I : financeFeatures.keySet()) {
			System.out.println("      Enter " + I + " to " + financeFeatures.get(I).choiceName);
	}
}

	@Override
	public void showPaymentsMenu() {
		
		Map<Integer, Feature> PaymentsMenu = new TreeMap<>();
		
		Feature viewRemainingPayments = new Feature("view all remaining payments.");
		Feature makePayment = new Feature("make a payment");
		Feature customerMenu = new Feature("go back to customer menu");
		
		PaymentsMenu.put(1, viewRemainingPayments);
		PaymentsMenu.put(2, makePayment);
		PaymentsMenu.put(3, customerMenu);
		
		//Art credit is from: �ASCII Art Money - Asciiart.Eu.�
		System.out.println("||====================================================================||\r\n"
				+ "||//$\\\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\//$\\\\||\r\n"
				+ "||(100)==================| FEDERAL RESERVE NOTE |================(100)||\r\n"
				+ "||\\\\$//        ~         '------========--------'                \\\\$//||\r\n"
				+ "||<< /        /$\\              // ____ \\\\                         \\ >>||\r\n"
				+ "||>>|  12    //L\\\\            // ///..) \\\\         L38036133B   12 |<<||\r\n"
				+ "||<<|        \\\\ //           || <||  >\\  ||                        |>>||\r\n"
				+ "||>>|         \\$/            ||  $$ --/  ||        One Hundred     |<<||\r\n"
				+ "||<<|      L38036133B        *\\\\  |\\_/  //* series                 |>>||\r\n"
				+ "||>>|  12                     *\\\\/___\\_//*   1989                  |<<||\r\n"
				+ "||<<\\      Treasurer     ______/Franklin\\________     Secretary 12 />>||\r\n"
				+ "||//$\\                 ~|UNITED STATES OF AMERICA|~               /$\\\\||\r\n"
				+ "||(100)===================  ONE HUNDRED DOLLARS =================(100)||\r\n"
				+ "||\\\\$//\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\\\$//||\r\n"
				+ "||====================================================================||");
		System.out.println("-----------------------Finance Menu-------------------------");
		for(Integer I: PaymentsMenu.keySet()) {
			System.out.println("Enter " + I + " to " + PaymentsMenu.get(I).choiceName);
		}
		
	}

	@Override
	public void showManagerMenu() {
		
		Map<Integer, Feature> managerFeatures = new TreeMap<>();
		
		Feature employeeFunctions = new Feature("to do employee functions.");
		Feature addEmployee = new Feature("to add an employee to the system.");
		Feature fireEmployee = new Feature("to fire an employee from the system.");
		Feature viewOfferHistory = new Feature("to view offer history.");
		Feature viewWinningBids = new Feature("to view winning bids.");
		Feature viewAssociates = new Feature("to view all associates.");
		Feature logOff = new Feature("to log off.");
		
		managerFeatures.put(1, employeeFunctions);
		managerFeatures.put(2, addEmployee);
		managerFeatures.put(3, fireEmployee);
		managerFeatures.put(4, viewOfferHistory);
		managerFeatures.put(5, viewWinningBids);
		managerFeatures.put(6, viewAssociates);
		managerFeatures.put(7, logOff);
		
		for (Integer I : managerFeatures.keySet()) {
			System.out.println("      Enter " + I + " to " + managerFeatures.get(I).choiceName);
	}
}


}
