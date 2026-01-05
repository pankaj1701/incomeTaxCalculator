package com.incometaxcalculator.taxcalculator;

public class NewRegineTaxCalculator extends BaseTaxCalculator {
        public double calculateTax(double taxableIncome) {
            double tax=0;
            if(taxableIncome>1200000)
            {
                if (taxableIncome <= 1500000)
                {
                    tax = (0.05 * (taxableIncome - 1200000));
                }
                else if (taxableIncome <= 1800000)
                {
                    tax = (15000 + 0.10 * (taxableIncome - 1500000));
                }
                else if (taxableIncome <= 2100000)
                {
                    tax = 45000 + 0.15 * (taxableIncome - 1800000);
                }
                else if (taxableIncome <= 2400000)
                {
                    tax = 90000 + 0.20 * (taxableIncome - 2100000);
                } else if (taxableIncome <= 3000000)
                {
                    tax = 150000 + 0.25 * (taxableIncome - 2400000);
                }
                else
                {
                    tax = 300000 + 0.30 * (taxableIncome - 3000000);
                }
            }
            //System.out.println("tax is: "+tax);
            return tax;
        }

    @Override
    public double calculateTaxableIncome(double totalIncome) {
        return totalIncome-75000;
    }

}
