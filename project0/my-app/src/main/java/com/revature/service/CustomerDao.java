package com.revature.service;

import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Sword;

public interface CustomerDao {
	void createAccount(Customer draft);
	void loginToAccount();
	void logOff();
	boolean authenticateUser(String username, String password);
	Customer customerMenu(Customer customer);
	void showAllAuctionSwords();
	void showSwordsByType();
	Customer auctionAccount(Customer customer);
	Customer auctionAccountInput(Customer customer);
	Customer bidOnSwords(Customer customer);
	Customer showPendingOffers(Customer customer);
	Customer showWinningBids(Customer customer);
	Customer showPayments(Customer customer);
	Customer finance(Customer customer, List<Sword> winningSwords);
	Customer payOffWhole(Customer customer, int swordId);
	Customer viewOwnedSwords(Customer customer);
	Customer payOneYear(Customer customer, int swordId, double swordAmount);
	Customer payTwoYear(Customer customer, int swordId, double swordAmount);
	Customer paymentsAccount(Customer customer);
	Customer paymentsAccountInput(Customer customer);
	Customer showAllPayments(Customer customer);
	Customer submitPayment(Customer customer);
}
