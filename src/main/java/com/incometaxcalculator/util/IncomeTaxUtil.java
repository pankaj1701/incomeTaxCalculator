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

public class IncomeTaxUtil {
    public static final Logger logger = LogManager.getLogger(IncomeTaxUtil.class);


    public static void printTaxDetails(TaxDetails taxDetails) {
        if(taxDetails != null)
        {
            logger.info(taxDetails);
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
        if(income == null)
        {
            throw new TaxCalculatorException("input cannot be null");
        }
    }
}