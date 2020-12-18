package com.company;

public class PayPalPayment extends Payment{
	
	String userEmail; //only user's email associated with paypal account is needed for this type of payment
	
	PayPalPayment (String userEmail, String billingNameFirst, String billingNameLast, String billingAddress, double totalCost)
	{
		super(billingNameFirst, billingNameLast, billingAddress, totalCost);
		this.userEmail = userEmail;
		
	}
	
	public String getUserEmail()
	{
		return userEmail;
	}
	
	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public void displayInfo()
	{
		System.out.println("Billing Name: " + billingNameFirst + " " + billingNameLast );
		System.out.println("Billing Address: " + billingAddress);
		System.out.println("Paypal account: " + userEmail);
		System.out.println("Total bill: $"+ totalCost);
	}

}
