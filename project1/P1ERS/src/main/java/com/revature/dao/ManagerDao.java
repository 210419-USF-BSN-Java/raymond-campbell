package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface ManagerDao {
	
	void login();
	User authenticateUser(String username, String password);
	void logout();
	boolean approveReimbRequest(int managerId, int reimbId);
	boolean denyReimbRequest(int managerId, int reimbId);
	List<Reimbursement> viewAllReimbRequests();
	void viewAllReimbReceipts();
	List<Reimbursement> viewResolvedRequests();
	List<User> viewAllEmployees();
	List<Reimbursement> viewReimbRequestById(int Id);
	User getUserById(int userId);
	void submitReimbReceipt();

}
