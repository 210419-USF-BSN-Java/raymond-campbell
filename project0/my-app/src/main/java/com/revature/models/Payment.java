package com.revature.models;

import com.revature.service.EmployeeDaoImpl;

public class Payment extends EmployeeDaoImpl{
	
	private int paymentNumber;
	private int swordNumber;
	private double totalBalance;
	private double weeklyPayment;
	private int paymentsLeft;
	private boolean paidOff;
	public Payment(int paymentNumber, int swordNumber, double totalBalance, double weeklyPayment, int paymentsLeft,
			boolean paidOff) {
		super();
		this.paymentNumber = paymentNumber;
		this.swordNumber = swordNumber;
		this.totalBalance = totalBalance;
		this.weeklyPayment = weeklyPayment;
		this.paymentsLeft = paymentsLeft;
		this.paidOff = paidOff;
	}
	public int getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(int paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public int getSwordNumber() {
		return swordNumber;
	}
	public void setSwordNumber(int swordNumber) {
		this.swordNumber = swordNumber;
	}
	public double getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}
	public double getWeeklyPayment() {
		return weeklyPayment;
	}
	public void setWeeklyPayment(double weeklyPayment) {
		this.weeklyPayment = weeklyPayment;
	}
	public int getPaymentsLeft() {
		return paymentsLeft;
	}
	public void setPaymentsLeft(int paymentsLeft) {
		this.paymentsLeft = paymentsLeft;
	}
	public boolean isPaidOff() {
		return paidOff;
	}
	public void setPaidOff(boolean paidOff) {
		this.paidOff = paidOff;
	}

	public String toString() {
		return "Payment [Payment Number: " + paymentNumber + ", Sword Number: " + swordNumber + ", Total Balance: $"
				+ totalBalance + "0, Weekly Payment: $" + weeklyPayment + "0, Payments Left: " + paymentsLeft + ", Paid Off? "
				+ paidOff + "]";
	}
	
	

}
