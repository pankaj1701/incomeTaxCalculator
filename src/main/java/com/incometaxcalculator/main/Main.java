package com.incometaxcalculator.main;

import com.incometaxcalculator.common.RegimeType;
import com.incometaxcalculator.model.AdvanceTax;
import com.incometaxcalculator.model.PayeeInfo;
import com.incometaxcalculator.model.TaxDetails;
import com.incometaxcalculator.model.TaxFilingDetails;
import com.incometaxcalculator.taxcalculator.TaxCalculator;

import com.incometaxcalculator.util.IncomeTaxUtil;
import com.incometaxcalculator.util.TaxLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) {
        System.out.println("IncomeTaxCalculator - Processing from File");
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));


        String filePath = "users_data.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            TaxCalculator taxCalculator = new TaxCalculator();

            int j = 0 ;
            while ((line = br.readLine()) != null) {
                j++ ;

                if (line.trim().isEmpty() || j == 1)  {
                    logger.info("Ignoring empty line and header");
                    continue;
                }

                logger.info(line);

                try {
                    String[] data = line.split(",");

                    for(int i=0; i<data.length; i++) {

                        data[i] = data[i].trim();
                       // logger.info(data[i]);
                    }

                    PayeeInfo payee = new PayeeInfo();
                    payee.setName(data[0].trim());
                    payee.setAge(Integer.parseInt(data[1]));
                    payee.setPanNumber(data[2]);

                    TaxFilingDetails taxFilingDetails = new TaxFilingDetails();
                    taxFilingDetails.setPayeeInfo(payee);

                    taxFilingDetails.setTaxableIncome(Double.parseDouble(data[3]));
                    taxFilingDetails.setIncome(Double.parseDouble(data[4]));
                    taxFilingDetails.setRegimeType(RegimeType.valueOf(data[5].toUpperCase()));
                    taxFilingDetails.setStcg(Double.parseDouble(data[6]));
                    taxFilingDetails.setLtcg(Double.parseDouble(data[7]));
                    taxFilingDetails.setTDS(Double.parseDouble(data[8]));

                    String advanceTaxStr = data[9];
                    if (!advanceTaxStr.isEmpty()) {
                        String[] entries = advanceTaxStr.split(";");
                        for (String entry : entries) {
                            String[] parts = entry.split(":");
                            if (parts.length == 2) {
                                taxFilingDetails.getAdvanceTax().add(new AdvanceTax(Double.parseDouble(parts[0]), parts[1]));
                            }
                        }
                    }

                    taxFilingDetails.setIncomeFromInterest(Double.parseDouble(data[10]));
                    taxFilingDetails.setIncomeFromOtherSources(Double.parseDouble(data[11]));

                    String filingDateInput = data[12];
                    String assessmentYear = data[13];

                    taxFilingDetails.setAssessmentYear(assessmentYear);
                    if(data.length == 15 && !data[14].trim().isEmpty())
                    {
                        taxFilingDetails.setExtendedDate(data[14]);
                    }

                    taxFilingDetails.setFilingDate(filingDateInput);

                    System.out.println("--------------------------------------------------");
                    System.out.println("Processing User: " + payee.getName());

                    TaxLogger.logFilingDetails(taxFilingDetails);
                    TaxDetails tax = taxCalculator.calculateTotalTax(taxFilingDetails);
                    TaxLogger.logCalculationResults(tax);
                    IncomeTaxUtil.printTaxDetails(tax);

                } catch (Exception e) {
                    System.err.println("Error processing line: " + line);
                    System.err.println("Reason: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}