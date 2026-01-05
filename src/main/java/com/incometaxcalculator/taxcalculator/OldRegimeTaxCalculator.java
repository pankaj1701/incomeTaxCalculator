package com.incometaxcalculator.taxcalculator;

import com.incometaxcalculator.model.Surcharge;

public class OldRegimeTaxCalculator extends BaseTaxCalculator {
    public double calculateTax(double taxableIncome) {
        throw new UnsupportedOperationException("Old regime is not yet supported");
    }
    public Surcharge calculateSurcharge(double taxableIncome, double incomeTax) {
        if (taxableIncome > 50000000) {
            Surcharge surcharge = new Surcharge();
            surcharge.setSurcharge(incomeTax * 0.37);
            surcharge.setRate(37);
            return surcharge;
        }
        return super.calculateSurcharge(taxableIncome, incomeTax);
    }

    @Override
    public double calculateTaxableIncome(double totalIncome) {
        return 0;
    }
}
