package com.revature.models;

import com.revature.service.CustomerDaoImpl;

public class Customer extends CustomerDaoImpl{
	
	private int customerNumber;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	public Customer(int customerNumber, String username, String password, String firstName, String lastName) {
		super();
		this.customerNumber = customerNumber;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
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

	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}