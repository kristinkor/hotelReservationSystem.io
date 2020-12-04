package com.company;

public class CardPayment extends Payment{
	
	String cardType; //Visa, Mastercard, Amex, etc...
	String cardNumber; //card number is 16 digits typically, which is too long for int
	int cvv2; //card security number on back
	int cardExpMonth; //expiration month of card
	int cardExpYear; //expiration year of card
	
	CardPayment(String cardType, String cardNumber, int cvv2, int cardExpMonth, int cardExpYear, String billingNameFirst, String billingNameLast, String billingAddress, double totalCost)
	{
		super(billingNameFirst, billingNameLast, billingAddress, totalCost);
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.cvv2 = cvv2;
		this.cardExpMonth = cardExpMonth;
		this.cardExpYear = cardExpYear;
	}
	
	public void displayInfo()
	{
		System.out.println("Billing Name: " + billingNameFirst + " " + billingNameLast );
		System.out.println("Billing Address: " + billingAddress);
		System.out.println("Card type: " + cardType);
		System.out.println("Card number: " + cardNumber);
		System.out.println("CVV2: " + cvv2);
		System.out.println("Card expires: " + cardExpMonth + " / " + cardExpYear);
		System.out.println("Total bill: $"+ totalCost);
	}
	
	
	

}
