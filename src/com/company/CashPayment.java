package com.company;

public class CashPayment extends Payment{
	
	//three components needed when accepting a written check as payment
	String routingNumber;
	String accountNumber;
	String checkNumber;
	
	CashPayment (String routingNumber, String accountNumber, String checkNumber, String billingNameFirst, String billingNameLast, String billingAddress, double totalCost)
	{
		super(billingNameFirst, billingNameLast, billingAddress, totalCost);
		this.routingNumber = routingNumber;
		this.accountNumber = accountNumber;
		this.checkNumber = checkNumber;
	}
	
	public void displayInfo()
	{
		System.out.println("Billing Name: " + billingNameFirst + " " + billingNameLast );
		System.out.println("Billing Address: " + billingAddress);
		System.out.println("Check information below:");
		System.out.println("Check routing number: " + routingNumber);
		System.out.println("Check account number: " + accountNumber);
		System.out.println("Check number: " + checkNumber);
		System.out.println("Total bill: $"+ totalCost);
	}
	

}
