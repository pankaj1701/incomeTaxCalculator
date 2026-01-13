package com.incometaxcalculator.util;

import com.incometaxcalculator.common.RegimeType;
import com.incometaxcalculator.exception.TaxCalculatorException;
import com.incometaxcalculator.interestcalculator.Section234AInterestCalculator;
import com.incometaxcalculator.model.TaxDetails;
import com.incometaxcalculator.model.TaxFilingDetails;
import com.incometaxcalculator.taxcalculator.BaseTaxCalculator;
import com.incometaxcalculator.taxcalculator.NewRegineTaxCalculator;
import com.incometaxcalculator.taxcalculator.OldRegimeTaxCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class IncomeTaxUtil {
    public static final Logger logger = LogManager.getLogger(IncomeTaxUtil.class);


    public static void printTaxDetails(TaxDetails taxDetails) {
        if(taxDetails != null)
        {
            logger.debug(taxDetails);
        }
    }
    public static BaseTaxCalculator regimeSelector (RegimeType regime) {
        BaseTaxCalculator taxCalculator = null;
        if(regime == RegimeType.NEW) {
            taxCalculator = new NewRegineTaxCalculator();
        } else if(regime == RegimeType.OLD) {
            taxCalculator = new OldRegimeTaxCalculator();
        }
        return taxCalculator;
    }

    public static void validate(TaxFilingDetails income) throws TaxCalculatorException {
        if(income.getIncome() <= 0)
        {
            throw new TaxCalculatorException("input cannot be negative ");
        }
        if(income.getLtcg() <= 0 )
        {
            throw new TaxCalculatorException("Ltcg cannot be negative");
        }
        if(income.getStcg() <= 0 )
        {
            throw new TaxCalculatorException("Stcg cannot be negative");

        }
        if(income.getTDS() <= 0 )
        {
            throw new TaxCalculatorException("TDS cannot be negative");

        }
        if(income.getTaxableIncome() <= 0 || income.getIncomeFromInterest() <= 0 || income.getIncomeFromOtherSources() <=0  )
        {
            throw new TaxCalculatorException("Any income cannot be negative");

        }
        LocalDate local_Date = LocalDate.parse(income.getFilingDate());
        // filling date must be a date
        // filing daet must be greater than 31st march of start if assessment year
        // assessment year (2024 - 2025)
        // assessment year should be seperated by hyphon ( - ),  both left and right part must be valid years
        // difference in both the years should be of one year

    }
}