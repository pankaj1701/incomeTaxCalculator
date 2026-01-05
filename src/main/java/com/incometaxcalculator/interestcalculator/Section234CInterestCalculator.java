package com.incometaxcalculator.interestcalculator;

import com.incometaxcalculator.model.AdvanceTax;
import com.incometaxcalculator.model.TaxFilingDetails;

import java.util.List;

public class Section234CInterestCalculator {

    /**
     * adv1 = 5000 on 12th june  2024
     * adv2 = 3000 on 9th  sept  2024
     * adv3 = 9000 on 13th march 2025
     * tax liability = 1,00,000
     * sort list by date in ascending order
     * taxPaid = 0
     * for each item in advance tax list
     * if item.advanceTaxPaidDate > 15th June (financial year)
     * if taxPaid < 0.15 * taxPayable
     * shortFall = (taxPayable * 0.15) - taxPaid
     * interest = shortFall * 0.01 * 3
     *
     * if item.advanceTaxPaidDate > 15th sept (financial year)
     *      * if taxPaid < 0.45 * taxPayable
     *      * shortFall = (taxPayable * 0.45) - taxPaid
     *      * interest = shortFall * 0.01 * 3
     *
     * * taxPaid += item.advanceTax
     *
     * if item.advanceTaxPaidDate > 15th dec (financial year)
     *      * if taxPaid < 0.75 * taxPayable
     *      * shortFall = (taxPayable * 0.75) - taxPaid
     *      * interest = shortFall * 0.01 * 3
     *
     * if item.advanceTaxPaidDate > 15th march  (financial year)
     *      * if taxPaid < taxPayable
     *      * shortFall = (taxPayable ) - taxPaid
     *      * interest = shortFall * 0.01
     *
     * @param taxFilingDetails
     * @param taxLiablity
     * @return
     */
    public static double calculateInterest(TaxFilingDetails taxFilingDetails, double taxLiablity) {
        List<AdvanceTax> advanceTaxList = taxFilingDetails.getAdvanceTax();



    }
}


