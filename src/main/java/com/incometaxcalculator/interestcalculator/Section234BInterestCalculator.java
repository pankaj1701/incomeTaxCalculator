package com.incometaxcalculator.interestcalculator;

import com.incometaxcalculator.model.AdvanceTax;
import com.incometaxcalculator.model.TaxFilingDetails;
import com.incometaxcalculator.util.TaxLogger;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Section234BInterestCalculator {
    public static final Logger logger = LogManager.getLogger(Section234BInterestCalculator.class);

    public static double calculateInterest(TaxFilingDetails taxFilingDetails, double taxLiablity) {
        logger.debug("--------------------");
        logger.debug("entering into calculateinterest");

        List<AdvanceTax> advanceTaxList = taxFilingDetails.getAdvanceTax();
        if (advanceTaxList != null) {
            advanceTaxList.sort(Comparator.comparing(AdvanceTax::getAdvanceTaxPaidDate));
                logger.debug("Advance tax in order : " + advanceTaxList);
        }
        String assesmentYearPart = taxFilingDetails.getAssessmentYear().split("-")[0];
        String financialYearEndDateStr = assesmentYearPart + "-03-31";
        LocalDate financialYearEndDate = LocalDate.parse(financialYearEndDateStr);
        logger.debug("Financial Year End Date : " + financialYearEndDateStr);

        double advanceTaxPaidDuringFinancialYear = 0;
        if (advanceTaxList != null) {
            for (AdvanceTax advTax : advanceTaxList) {
                if (!advTax.getAdvanceTaxPaidDate().isAfter(financialYearEndDate)) {
                    advanceTaxPaidDuringFinancialYear += advTax.getAdvanceTax();
                }
            }
        }
        logger.debug("Advance tax paid during financial year : " + advanceTaxPaidDuringFinancialYear);

        if (advanceTaxPaidDuringFinancialYear >= taxLiablity * 0.90) {
            logger.debug("90% tax already paid before 31st march of financial year, hence Section234B interest is not applicable");
            return 0;
        }

        logger.debug("90% tax not paid before 31st march of financial year, hence Section234B interest is applicable.");

        double shortFall = taxLiablity - advanceTaxPaidDuringFinancialYear;
        logger.debug("Initial short fall : " + shortFall);

        double totalInterest = 0;
        LocalDate filingDate = LocalDate.parse(taxFilingDetails.getFilingDate());

        LocalDate interestStartDate = financialYearEndDate.plusDays(1);

        YearMonth interestStartMonth = YearMonth.from(interestStartDate);
        YearMonth filingMonth = YearMonth.from(filingDate);

        while (!interestStartMonth.isAfter(filingMonth)) {

            if (shortFall > 0)
            {
                double monthlyInterest = shortFall * 0.01;
                totalInterest += monthlyInterest;
                logger.debug("Month: " + interestStartMonth + " | Interest: " + monthlyInterest + " | Based on Shortfall: " + shortFall);
            }

            if (advanceTaxList != null)
            {
                for (AdvanceTax advTax : advanceTaxList)
                {
                    LocalDate paymentDate = advTax.getAdvanceTaxPaidDate();
                    if (paymentDate.isAfter(financialYearEndDate))
                    {
                        YearMonth paymentYearMonth = YearMonth.from(paymentDate);
                        if (paymentYearMonth.equals(interestStartMonth))
                        {
                            shortFall -= advTax.getAdvanceTax();
                            logger.debug("   -> Payment detected: " + advTax.getAdvanceTax() + " in " + interestStartMonth + ". Reduced Shortfall for next month: " + shortFall);
                        }
                    }
                }
            }

            interestStartMonth = interestStartMonth.plusMonths(1);
        }

        logger.debug("Total Interest to pay : " + totalInterest);
        logger.debug("Exiting calculateinterest");
        logger.debug("--------------------");

        return totalInterest;
    }
}