package com.incometaxcalculator.interestcalculator;

import com.incometaxcalculator.model.Interest;
import com.incometaxcalculator.model.TaxDetails;
import com.incometaxcalculator.model.TaxFilingDetails;

import static com.incometaxcalculator.interestcalculator.Section234AInterestCalculator.logger;

public class InterestCalculator {

    public static Interest calculateInterest(TaxFilingDetails taxFilingDetails , TaxDetails taxDetails) {
        Interest interest = new Interest();

        logger.debug("totaling all the interests ");

        double interest234A = Section234AInterestCalculator.calculateInterest(taxDetails.getTaxPayable(),taxFilingDetails.getFilingDate(), taxFilingDetails.getAssessmentYear());
        double interest234B = Section234BInterestCalculator.calculateInterest(taxFilingDetails,taxDetails.getTaxPayable());
        double interest234C = Section234CInterestCalculator.calculateInterest(taxFilingDetails,taxDetails.getTaxPayable());

        interest.setInterest234A(interest234A);
        interest.setInterest234B(interest234B);
        interest.setInterest234C(interest234C);

        logger.debug("Total interest is  " + interest);
        return interest;
    }

}

