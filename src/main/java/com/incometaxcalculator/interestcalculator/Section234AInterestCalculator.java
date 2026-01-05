package com.incometaxcalculator.interestcalculator;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Section234AInterestCalculator {

    public static final Logger logger = LogManager.getLogger(Section234AInterestCalculator.class);

    public static double calculateInterest(double taxDue , String filingDate , String assessmentYear)
    {
        logger.debug("--------------------");
        logger.debug("entering into calculateInterest for 234A");

        LocalDate date = LocalDate.parse(filingDate);
        assessmentYear = assessmentYear.split("-")[0];
        String lastDate = assessmentYear+"-07-31";
        LocalDate dueDate = LocalDate.parse(lastDate);
        logger.debug("Due Date : " + lastDate);

        LocalDate startMonth = date.withDayOfMonth(1);
        LocalDate endMonth = dueDate.withDayOfMonth(1);

        long diffInMonths = ChronoUnit.MONTHS.between(endMonth,startMonth);
        logger.debug("diffInMonths: " + diffInMonths);
        double totalInterest = 0;

        if(diffInMonths>=0)
        {
            logger.info("Filing date is after due date, hence Section234A interest is applicable.");
            totalInterest = diffInMonths*taxDue*0.01;
            logger.debug("Total Interest to pay under 234A is : " + totalInterest);
            logger.debug("Exiting calculateInterest of 234A");
            logger.debug("--------------------");

        }
        return totalInterest;
    }
}