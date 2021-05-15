package com.revature.models;

public class Sword {
	
	private int swordNumber;
	private String type;
	private String name;
	private double current_offer;
	private boolean available;
	
	
	public Sword(int swordNumber, String type, String name, double current_offer, boolean available) {
		super();
		this.swordNumber = swordNumber;
		this.type = type;
		this.name = name;
		this.current_offer = current_offer;
		this.available = available;
	}
	
	


	public Sword(int swordNumber, String type, String name, double current_offer) {
		super();
		this.swordNumber = swordNumber;
		this.type = type;
		this.name = name;
		this.current_offer = current_offer;
	}

	


	public Sword(int swordNumber, String type, String name) {
		super();
		this.swordNumber = swordNumber;
		this.type = type;
		this.name = name;
	}




	public int getSwordNumber() {
		return swordNumber;
	}


	public void setSwordNumber(int swordNumber) {
		this.swordNumber = swordNumber;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getCurrent_offer() {
		return current_offer;
	}


	public void setCurrent_offer(double current_offer) {
		this.current_offer = current_offer;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}


	@Override
	public String toString() {
		return "Sword [Sword Number = " + swordNumber + ", Sword Type = " + type + ", Sword Name =" + name + ", Current Offer = $"
				+ current_offer + "0]  \n";
	}
	
	

}

