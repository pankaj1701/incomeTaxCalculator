package com.incometaxcalculator.main;

import com.incometaxcalculator.common.RegimeType;
import com.incometaxcalculator.model.AdvanceTax;
import com.incometaxcalculator.model.PayeeInfo;
import com.incometaxcalculator.model.TaxDetails;
import com.incometaxcalculator.model.TaxFilingDetails;
import com.incometaxcalculator.taxcalculator.TaxCalculator;
import com.incometaxcalculator.util.IncomeTaxUtil;
import com.incometaxcalculator.util.TaxLogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("IncomeTaxCalculator - Processing from File");
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        String filePath = "users_data.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            TaxCalculator taxCalculator = new TaxCalculator();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                try {
                    // Use -1 limit to keep trailing empty strings (important if date is missing)
                    String[] data = line.split(",", -1);

                    for(int i=0; i<data.length; i++) data[i] = data[i].trim();

                    // --- 1. Basic Info ---
                    PayeeInfo payee = new PayeeInfo();
                    payee.setName(data[0]);
                    payee.setAge(Integer.parseInt(data[1]));
                    payee.setPanNumber(data[2]);

                    TaxFilingDetails taxFilingDetails = new TaxFilingDetails();
                    taxFilingDetails.setPayeeInfo(payee);

                    // --- 2. Income Details ---
                    taxFilingDetails.setTaxableIncome(Double.parseDouble(data[3]));
                    taxFilingDetails.setIncome(Double.parseDouble(data[4]));
                    taxFilingDetails.setRegimeType(RegimeType.valueOf(data[5].toUpperCase()));
                    taxFilingDetails.setStcg(Double.parseDouble(data[6]));
                    taxFilingDetails.setLtcg(Double.parseDouble(data[7]));
                    taxFilingDetails.setTDS(Double.parseDouble(data[8]));


                    // --- 3. Advance Tax ---
                    String advanceTaxStr = data[9];
                    if (!advanceTaxStr.isEmpty() && !advanceTaxStr.equals("0")) {
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

                    // --- 4. DATE LOGIC (Updated) ---
                    String filingDateInput = data[12];
                    String assessmentYear = data[13];
                    String extendedDate = data[14];

                    // Set Assessment Year first so we can use it for the default calculation
                    taxFilingDetails.setAssessmentYear(assessmentYear);
                    taxFilingDetails.setExtendedDate(extendedDate);

                    if (filingDateInput.isEmpty()) {
                        // User provided NO date -> Calculate Default (31 July of AY Start Year)
                        // Example: "2025-2026" -> Start Year is "2025" -> "2025-07-31"
                        String startYear = assessmentYear.split("-")[0];
                        String defaultDate = startYear + "-07-31";

                        taxFilingDetails.setFilingDate(defaultDate);
                        System.out.println("Note: No filing date for " + payee.getName() + ". Using Default: " + defaultDate);
                    } else {
                        // User provided a date -> Use it
                        taxFilingDetails.setFilingDate(filingDateInput);
                    }

                    // --- 5. Process ---
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