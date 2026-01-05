package com.incometaxcalculator.taxcalculator;

import com.incometaxcalculator.exception.TaxCalculatorException;
import com.incometaxcalculator.interestcalculator.InterestCalculator;
import com.incometaxcalculator.interestcalculator.Section234AInterestCalculator;
import com.incometaxcalculator.interestcalculator.Section234BInterestCalculator;
import com.incometaxcalculator.model.Interest;
import com.incometaxcalculator.model.TaxFilingDetails;
import com.incometaxcalculator.model.Surcharge;
import com.incometaxcalculator.model.TaxDetails;
import com.incometaxcalculator.util.IncomeTaxUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaxCalculator {
    public static final Logger logger = LogManager.getLogger(TaxCalculator.class);
    public TaxDetails calculateTotalTax(TaxFilingDetails income) {
        TaxDetails taxdetails = new TaxDetails();
        try {
            IncomeTaxUtil.validate(income);
        } catch (TaxCalculatorException e) {
            logger.error("Got exception while validating input: " ,e.getMessage());
            return null;

        }


        taxdetails.setPayeeInfo(income.getPayeeInfo());
        taxdetails.setIncome(income.getIncome());
        taxdetails.setIncomeFromInterest(income.getIncomeFromInterest());
        taxdetails.setIncomeFromOtherSources(income.getIncomeFromOtherSources());

        BaseTaxCalculator taxCalculator = IncomeTaxUtil.regimeSelector(income.getRegimeType());
        double taxableIncome = taxCalculator.calculateTaxableIncome(income.getTotalIncome());
        double tax = taxCalculator.calculateTax(taxableIncome);
        //System.out.println("Saarthak: "+tax);
        taxdetails.setTaxableIncome(taxableIncome);
        taxdetails.setIncomeTax(tax);
        taxdetails.setTDS(income.getTDS());
        taxdetails.setAdvnTax(income.getTotalAdvanceTaxPaid());

        double stcgTax = taxCalculator.calculateStcg(income.getStcg());
        taxdetails.setStcgTax(stcgTax);
        double ltcgTax = taxCalculator.calculateLtcg(income.getLtcg());
        taxdetails.setLtcgTax(ltcgTax);

        double totalTax = tax + taxdetails.getStcgTax() + taxdetails.getLtcgTax();
        taxdetails.setTotalTax(totalTax);
        Surcharge surcharge = taxCalculator.calculateSurcharge(taxableIncome, totalTax);
        taxdetails.setSurcharge(surcharge);
        double cess = taxCalculator.calculateCess(surcharge.getSurcharge() + totalTax);
        taxdetails.setHECess(cess);
        Interest interest = InterestCalculator.calculateInterest(income,taxdetails);
        taxdetails.setInterest(interest);

        return taxdetails;
    }
}