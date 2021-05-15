package com.revature.service;

import com.revature.models.Employee;
import com.revature.models.Manager;

public interface ManagerDao {
	Manager managerMenu(Manager manager);
	Manager addEmployee(Manager manager);
	Manager createAccount(Manager manager, Employee employee);
	Manager fireEmployee(Manager manager);
	void viewAllEmployees();
	void viewAllOffers();
	void viewWinningBids();
}
