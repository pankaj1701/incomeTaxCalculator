package com.incometaxcalculator.main;

import com.incometaxcalculator.common.RegimeType;
import com.incometaxcalculator.exception.TaxCalculatorException;
import com.incometaxcalculator.interestcalculator.Section234BInterestCalculator;
import com.incometaxcalculator.model.AdvanceTax;
import com.incometaxcalculator.model.PayeeInfo;
import com.incometaxcalculator.model.TaxDetails;
import com.incometaxcalculator.model.TaxFilingDetails;
import com.incometaxcalculator.taxcalculator.TaxCalculator;
import com.incometaxcalculator.util.IncomeTaxUtil;
import com.incometaxcalculator.util.TaxLogger;

public class Main {

    public static void main(String[] args) {

        System.out.println("IncomeTaxCalculator");

        try {
            TaxCalculator taxCalculator = new TaxCalculator();

            PayeeInfo payee = new PayeeInfo();
            payee.setName("Saarthak");
            payee.setAge(20);
            payee.setPanNumber("1098765432");

            TaxFilingDetails taxFilingDetails = new TaxFilingDetails();
            taxFilingDetails.setPayeeInfo(payee);

            taxFilingDetails.setTaxableIncome(1800000);
            taxFilingDetails.setIncome(10000000);
            taxFilingDetails.setRegimeType(RegimeType.NEW);
            taxFilingDetails.setStcg(200000);
            taxFilingDetails.setLtcg(200000);
            taxFilingDetails.setTDS(10000);
            taxFilingDetails.getAdvanceTax().add(new AdvanceTax(10000, "2025-03-31"));
            taxFilingDetails.getAdvanceTax().add(new AdvanceTax(5000, "2025-06-02"));
            taxFilingDetails.getAdvanceTax().add(new AdvanceTax(10000, "2024-09-11"));
            taxFilingDetails.getAdvanceTax().add(new AdvanceTax(5000, "2025-05-30"));
            taxFilingDetails.setIncomeFromInterest(12000);
            taxFilingDetails.setIncomeFromOtherSources(32000);
            taxFilingDetails.setFilingDate("2025-09-05");
            taxFilingDetails.setAssessmentYear("2025-2026");

            //Section234BInterestCalculator.calculateInterest(taxFilingDetails, 90000);

            TaxLogger.logFilingDetails(taxFilingDetails);

            TaxDetails tax = taxCalculator.calculateTotalTax(taxFilingDetails);

            TaxLogger.logCalculationResults(tax);

            IncomeTaxUtil.printTaxDetails(tax);

        } catch (TaxCalculatorException e) {
            System.err.println("Tax Calculation Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}