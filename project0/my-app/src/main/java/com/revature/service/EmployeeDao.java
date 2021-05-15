package com.revature.service;

import com.revature.models.Employee;
import com.revature.models.Sword;

public interface EmployeeDao {
	void loginToAccount();
	void logOff();
	Boolean authenticateUser(String username, String password);
	Employee employeeMenu(Employee employee);
	void showAllPendingOffers();
	void showMaxOffersBySwordNumber();
	Employee acceptOffers(Employee employee);
	Employee rejectOffers(Employee employee);
	Employee addItems(Employee employee);
	Employee addSwordToSystem(Employee employee, Sword draft);
	Employee removeSwordFromSystem(Employee employee);
	Employee removeItems(Employee employee);
	Employee viewCustomerPayments(Employee employee);

}
