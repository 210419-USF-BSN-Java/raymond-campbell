package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface EmployeeDao {
	void login();
	User authenticateUser(String username, String password);
	void logout();
	boolean submitReimbRequest(Reimbursement reimbursement);
	void submitReimbReceipt();
	List<Reimbursement> viewPendingReimb(int userId);
	List<Reimbursement> viewResolvedReimb(int userId);
	boolean updateInfo(User user);
	User getUserById(int userId);
	public List<User> viewAllEmployees();
}
