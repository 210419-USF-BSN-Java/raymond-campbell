package com.revature;

import org.apache.log4j.BasicConfigurator;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ManagerDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class Driver {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		EmployeeDaoImpl e = new EmployeeDaoImpl();
		ManagerDaoImpl m = new ManagerDaoImpl();
		String mUsername = "employee";
		String mPassword = "password";
		User manager = new User(2, "employee", "password", "john", "smith", "johnsmith@reimb.com", 1);
		String username = "employee";
		String password = "password";
		Reimbursement r = new Reimbursement(0, 0, password, 0, 0, 0, 0);
		r.setReimbAmount(100.69);
		r.setReimbDescription("practice");
		r.setReimbAuthor(2);
//		System.out.println(e.authenticateUser(username, password));
//		System.out.println(e.submitReimbRequest(r));
//		System.out.println(e.viewPendingReimb(2));
//		e.testMethod();
//		System.out.println(e.viewResolvedReimb(2));
//		System.out.println(e.authenticateUser(mUsername, mPassword));
//		System.out.println(m.viewAllReimbRequests());
//		System.out.println(m.viewResolvedRequests());
//		System.out.println(m.approveReimbRequest(3, 2));
//		System.out.println(m.denyReimbRequest(3, 3));
//		System.out.println(m.viewResolvedRequests());
//		System.out.println(m.viewAllEmployees());
//		System.out.println(e.updateInfo(manager));
	}

}
