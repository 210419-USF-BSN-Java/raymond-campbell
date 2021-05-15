package com.revature.models;

import com.revature.service.EmployeeDaoImpl;

public class Employee extends EmployeeDaoImpl{
	
	private int employeeNumber;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String title;
	
	public Employee(int employeeNumber, String username, String password, String firstName, String lastName,
			String title) {
		super();
		this.employeeNumber = employeeNumber;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Employee [employeeNumber=" + employeeNumber + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title + "]";
	}
	
	
	

}
