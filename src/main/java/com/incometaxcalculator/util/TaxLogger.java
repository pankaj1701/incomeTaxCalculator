package com.incometaxcalculator.util;

import com.incometaxcalculator.model.TaxDetails;
import com.incometaxcalculator.model.TaxFilingDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaxLogger {

    public static final Logger logger = LogManager.getLogger(TaxLogger.class);


    /**
     * Logs the initial details provided for tax filing.
     * @param details The input details for the tax calculation.
     */
    public static void logFilingDetails(TaxFilingDetails details) {
        if (details == null) {
            logger.error("Attempted to log null TaxFilingDetails.");
            throw new RuntimeException("TaxFilingDetails can not be null.");
        }


        logger.debug("--------------------");
        logger.debug("Payee: {}", details.getPayeeInfo() != null ? details.getPayeeInfo().getName() : "Unknown");
        logger.debug("Assessment Year: {}", details.getAssessmentYear());
        logger.debug("Regime Type: {}", details.getRegimeType());
        logger.debug("Total Income Declared: {}", details.getIncome());
        logger.debug("Filing Date: {}", details.getFilingDate());

        if (details.getAdvanceTax() != null && !details.getAdvanceTax().isEmpty()) {
            logger.debug("Advance Tax Entries Found: {}", details.getAdvanceTax().size());
        }
    }

    /**
     * Logs the final calculated tax breakdown.
     * @param taxDetails The result of the tax calculation.
     */
    public static void logCalculationResults(TaxDetails taxDetails) {
        if (taxDetails == null) {
            logger.error("Attempted to log null TaxDetails.");
            return;
        }

        logger.debug("--------------------");
        logger.debug("Taxable Income: {}", taxDetails.getTaxableIncome());
        logger.debug("Basic Income Tax: {}", taxDetails.getIncomeTax());
        logger.debug("Surcharge: {}", taxDetails.getSurcharge() != null ? taxDetails.getSurcharge().getSurcharge() : 0.0);
        logger.debug("Health & Education Cess: {}", taxDetails.getHECess());

        if (taxDetails.getInterest() != null) {
            logger.debug("Interest u/s 234A: {}", taxDetails.getInterest().getInterest234A());
            logger.debug("Interest u/s 234B: {}", taxDetails.getInterest().getInterest234B());
            logger.debug("Interest u/s 234C: {}", taxDetails.getInterest().getInterest234C());
            logger.debug("Total Interest: {}", taxDetails.getInterest().getTotalIntereast());
        }

        logger.debug("Total Tax Payable: {}", taxDetails.getTaxPayable());
        logger.debug("-----------------------------------------");
    }

    /**
     * Logs any exceptions or errors encountered during the process.
     * @param message Custom error message.
     * @param e The exception object.
     */
    public static void logError(String message, Exception e) {
        logger.error("{}: {}", message, e.getMessage());
        logger.debug("Stack Trace:", e);
    }
}