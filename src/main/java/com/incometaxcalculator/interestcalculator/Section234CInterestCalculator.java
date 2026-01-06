package com.incometaxcalculator.interestcalculator;

import com.incometaxcalculator.model.AdvanceTax;
import com.incometaxcalculator.model.TaxFilingDetails;

import java.time.LocalDate;
import java.util.List;

public class Section234CInterestCalculator {

    /**
     * adv1 = 5000 on 12th june  2024
     * adv2 = 3000 on 9th  sept  2024
     * adv3 = 9000 on 13th march 2025
     * tax liability = 1,00,000
     * sort list by date in ascending order
     * double[] quarter_wise_advance_tax =  new double[4]
     * double[] quarter_wise_required_percentage = {0.15 , 0.45 , 0.75 , 1}
     *
     * for each item in advance tax list
     * if advTaxPaid < 15th june ( assessment year)
     * double q1Tax = quarter_wise_advance_tax[0]
     * q1Tax += advTaxPaid
     *
     * else if advTaxPaid < 15th sept ( assessment year)
     *      * double q2Tax = quarter_wise_advance_tax[1]
     *      * q2Tax += advTaxPaid
     *
     * else if advTaxPaid < 15th dec (assessment year)
     *      * double q3Tax = quarter_wise_advance_tax[2]
     *      * q3Tax += advTaxPaid
     *
     * else if advTaxPaid < 15th march ( assessment year)
     *      * double q4Tax = quarter_wise_advance_tax[3]
     *      * q4Tax += advTaxPaid
     *
     * int interest_month = 3
     * double cumulative_payment = 0.0
     *
     * for (int i = 0 ; i < quarter_wise_advance_tax.length() ; i++){
     *      if i = 3 {
     *          interest_month  = 1
     *      }
     *      cumulative_payment += quarter_wise_advance_tax[i]
     *     required_payment = total_tax * quarter_wise_required_percentage[i]
     *     if required_payment < cumulative_payment
     *
     *     interest += (required_payment - cumulative_payment ) * 0.01 * interest_month
     * }
     *      * adv1 = 5000 on 12th june  2024
     *      * adv2 = 3000 on 9th  sept  2024
     *      * adv3 = 9000 on 13th march 2025
     *      * tax liability = 1,00,000
     *
     *
     *
     *
     *
     *
     *
     * @param taxFilingDetails
     * @param taxLiablity
     * @return
     */



    public static double calculateInterest(TaxFilingDetails taxFilingDetails, double taxLiablity)
    {
        List<AdvanceTax> advanceTaxList = taxFilingDetails.getAdvanceTax();
        double[] quarter_wise_advance_tax =  new double[4];
        int fyEnd = Integer.parseInt(taxFilingDetails.getAssessmentYear().split("-")[0]);
        int fyBegin = fyEnd-1;

        LocalDate qrt1 = LocalDate.of(fyBegin, 6 , 15);
        LocalDate qrt2 = LocalDate.of(fyBegin, 9 , 15);
        LocalDate qrt3 = LocalDate.of(fyBegin, 12 , 15);
        LocalDate qrt4 = LocalDate.of(fyEnd, 3 , 15);



        for (AdvanceTax advanceTax : advanceTaxList)
        {
         if ( advanceTax.getAdvanceTaxPaidDate().isBefore())
        }






        return 0 ;
    }
}




