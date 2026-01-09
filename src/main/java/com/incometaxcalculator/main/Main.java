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
        // 1. Print start message
        System.out.println("IncomeTaxCalculator - Processing from File");

        // 2. Define the file path
        String filePath = "users_data.txt";

        // 3. Open the file using try-with-resources to ensure it closes automatically
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 4. Instantiate the calculator logic
            TaxCalculator taxCalculator = new TaxCalculator();

            // 5. Loop through each line of the file until the end
            while ((line = br.readLine()) != null) {
                // 6. Skip empty lines to prevent errors
                if (line.trim().isEmpty()) continue;

                try {
                    // 7. Split the line by comma to get individual fields
                    String[] data = line.split(",");

                    // 8. Clean up whitespace around each field
                    for(int i=0; i<data.length; i++) data[i] = data[i].trim();

                    // 9. Create and populate PayeeInfo object (indices 0-2)
                    PayeeInfo payee = new PayeeInfo();
                    payee.setName(data[0]); // Name
                    payee.setAge(Integer.parseInt(data[1])); // Age
                    payee.setPanNumber(data[2]); // PAN

                    // 10. Create TaxFilingDetails and link the Payee
                    TaxFilingDetails taxFilingDetails = new TaxFilingDetails();
                    taxFilingDetails.setPayeeInfo(payee);

                    // 11. Populate basic financial details (indices 3-5)
                    taxFilingDetails.setTaxableIncome(Double.parseDouble(data[3]));
                    taxFilingDetails.setIncome(Double.parseDouble(data[4]));
                    taxFilingDetails.setRegimeType(RegimeType.valueOf(data[5].toUpperCase())); // NEW/OLD

                    // 12. Populate Capital Gains and TDS (indices 6-8)
                    taxFilingDetails.setStcg(Double.parseDouble(data[6]));
                    taxFilingDetails.setLtcg(Double.parseDouble(data[7]));
                    taxFilingDetails.setTDS(Double.parseDouble(data[8]));

                    // 13. Parse Advance Tax (index 9) using Semicolon delimiter
                    String advanceTaxStr = data[9];
                    // Check if advance tax data exists and is not just "0"
                    if (!advanceTaxStr.isEmpty() && !advanceTaxStr.equals("0")) {
                        // Split the single string into multiple entries using semicolon
                        String[] entries = advanceTaxStr.split(";");
                        for (String entry : entries) {
                            // Split each entry (e.g., "10000:2025-03-31") by colon
                            String[] parts = entry.split(":");
                            if (parts.length == 2) {
                                double amount = Double.parseDouble(parts[0]);
                                String date = parts[1];
                                // Add to the list
                                taxFilingDetails.getAdvanceTax().add(new AdvanceTax(amount, date));
                            }
                        }
                    }

                    // 14. Populate remaining income and dates (indices 10-13)
                    taxFilingDetails.setIncomeFromInterest(Double.parseDouble(data[10]));
                    taxFilingDetails.setIncomeFromOtherSources(Double.parseDouble(data[11]));
                    taxFilingDetails.setFilingDate(data[12]);
                    taxFilingDetails.setAssessmentYear(data[13]);

                    // 15. Execute Calculation and Logging
                    System.out.println("--------------------------------------------------");
                    System.out.println("Processing User: " + payee.getName());

                    TaxLogger.logFilingDetails(taxFilingDetails); // Log inputs
                    TaxDetails tax = taxCalculator.calculateTotalTax(taxFilingDetails); // Calculate
                    TaxLogger.logCalculationResults(tax); // Log results
                    IncomeTaxUtil.printTaxDetails(tax); // Print to console

                } catch (Exception e) {
                    // 16. Error handling for specific lines (e.g., bad number format)
                    System.err.println("Error processing line: " + line);
                    System.err.println("Reason: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            // 17. Error handling for file access issues (e.g., file not found)
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}