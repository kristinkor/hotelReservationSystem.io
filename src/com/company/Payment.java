package com.company;

public class Payment {


    String billingNameFirst; // these three strings can be different from the person staying
    String billingNameLast;
    String billingAddress;
    double totalCost;

    Payment(String billingNameFirst, String billingNameLast, String billingAddress, double totalCost) {
        this.billingNameFirst = billingNameFirst;
        this.billingNameLast = billingNameLast;
        this.billingAddress = billingAddress;
        this.totalCost = totalCost;

    }

    public String getBillingNameFirst() {
        return billingNameFirst;
    }

    public String getBillingNameLast() {
        return billingNameLast;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void addCharge(double charge) //you can add extra charges to visitor this way
    {
        totalCost += charge;
    }

}
