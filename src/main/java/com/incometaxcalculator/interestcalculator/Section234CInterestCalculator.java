package com.incometaxcalculator.interestcalculator;

import com.incometaxcalculator.model.AdvanceTax;
import com.incometaxcalculator.model.TaxFilingDetails;

import java.time.LocalDate;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Section234CInterestCalculator {
    public static final Logger logger = LogManager.getLogger(Section234CInterestCalculator.class);
    public static double calculateInterest(TaxFilingDetails taxFilingDetails, double taxLiablity)
    {
        List<AdvanceTax> advanceTaxList = taxFilingDetails.getAdvanceTax();
        double[] quarter_wise_advance_tax =  new double[4];
        double[] quater_wise_required_percentage = {0.15 , 0.45 , 0.75 , 1.00};
        int fyEnd = Integer.parseInt(taxFilingDetails.getAssessmentYear().split("-")[0]);
        int fyBegin = fyEnd-1;

        LocalDate qrt1 = LocalDate.of(fyBegin, 6 , 15);
        LocalDate qrt2 = LocalDate.of(fyBegin, 9 , 15);
        LocalDate qrt3 = LocalDate.of(fyBegin, 12 , 15);
        LocalDate qrt4 = LocalDate.of(fyEnd, 3 , 15);

        for(AdvanceTax advanceTax : advanceTaxList){

            if(!advanceTax.getAdvanceTaxPaidDate().isAfter(qrt1))
            {
                quarter_wise_advance_tax[0] += advanceTax.getAdvanceTax();
            }
            else if(!advanceTax.getAdvanceTaxPaidDate().isAfter(qrt2))
            {
                quarter_wise_advance_tax[1] += advanceTax.getAdvanceTax();
            }
            else if(!advanceTax.getAdvanceTaxPaidDate().isAfter(qrt3))
            {
                quarter_wise_advance_tax[2] += advanceTax.getAdvanceTax();
            }
            else if(!advanceTax.getAdvanceTaxPaidDate().isAfter(qrt4))
            {
                quarter_wise_advance_tax[3] += advanceTax.getAdvanceTax();
            }
        }
        int interestMonth = 3;
        double cumulative_payment = 0.0;
        double interest = 0;

        for(int i = 0 ; i<quarter_wise_advance_tax.length; i++)
        {
            if(i==3)
            {
                interestMonth = 1;
            }
            cumulative_payment += quarter_wise_advance_tax[i];
            double requiredPayment = taxLiablity * quater_wise_required_percentage[i];
            if(cumulative_payment < requiredPayment)
            {
                interest += (requiredPayment - cumulative_payment) * 0.01 * interestMonth;
            }
            logger.debug("Interest for Quarter" + (i+1) +": "+ interest + ", Total Tax Paid: " + cumulative_payment + ", Required Tax: " + requiredPayment);
        }
        logger.debug("Total Interest :"+interest);
        return interest ;
    }
}




