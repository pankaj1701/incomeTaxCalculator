package com.incometaxcalculator.taxcalculator;

import com.incometaxcalculator.model.Surcharge;

public abstract class BaseTaxCalculator {

    public abstract double calculateTax(double taxableIncome);
    public  double calculateLtcg(double ltcg){
        return ltcg*0.125 ;
    }
    public  double calculateStcg(double stcg){
        return stcg*0.20 ;
    }


    public Surcharge calculateSurcharge(double taxableIncome, double incomeTax) {
        double surchargeRate = 0;
        Surcharge surcharge = new Surcharge();
        if (taxableIncome > 20000000) {
            surchargeRate = 0.25;
        } else if (taxableIncome > 10000000) {
            surchargeRate = 0.15;
        } else if (taxableIncome > 5000000) {
            surchargeRate = 0.10;
        }
        surcharge.setSurcharge(incomeTax * surchargeRate);
        surcharge.setRate(surchargeRate * 100);
        return surcharge;
    }
    public double calculateCess (double incomeTaxWithSurcharge) {
        double cess = incomeTaxWithSurcharge * 0.04;
        return cess;
    }

    public abstract double calculateTaxableIncome(double totalIncome);
}

