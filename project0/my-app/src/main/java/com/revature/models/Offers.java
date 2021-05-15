package com.revature.models;

import com.revature.service.EmployeeDaoImpl;

public class Offers extends EmployeeDaoImpl{
	
	private int offer_number;
	private int sword_number;
	private int customer_number;
	private double offer_amount;
	
	public Offers(int offer_number, int sword_number, int customer_number, double offer_amount) {
		super();
		this.offer_number = offer_number;
		this.sword_number = sword_number;
		this.customer_number = customer_number;
		this.offer_amount = offer_amount;
	}
	
	public int getOffer_number() {
		return offer_number;
	}

	public void setOffer_number(int offer_number) {
		this.offer_number = offer_number;
	}

	public int getSword_number() {
		return sword_number;
	}

	public void setSword_number(int sword_number) {
		this.sword_number = sword_number;
	}

	public int getCustomer_number() {
		return customer_number;
	}

	public void setCustomer_number(int customer_number) {
		this.customer_number = customer_number;
	}

	public double getOffer_amount() {
		return offer_amount;
	}

	public void setOffer_amount(double offer_amount) {
		this.offer_amount = offer_amount;
	}

	public String toString() {
		return "Offers [Offer Number: " + offer_number + ", Sword Number: " + sword_number + ", Customer Number: "
				+ customer_number + ", Offer Amount: $" + offer_amount + "0]";
	}
	
	

}
