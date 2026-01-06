
        package com.incometaxcalculator.model;

import com.incometaxcalculator.common.RegimeType;
import com.incometaxcalculator.util.IncomeTaxUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

        public class TaxDetails {


            public PayeeInfo payeeInfo;

            double incomeTax;
            Surcharge surcharge;
            double totalTax;
            double taxDue;
            double taxableIncome;
            double income;
            RegimeType regimeType = RegimeType.NEW;

            double stcgTax;
            double ltcgTax;
            double HECess;
            double TDS;
            double advnTax;
            double taxPayable;
            double incomeFromInterest;
            double incomeFromOtherSources;
            double taxPayableBeforeInterest;

            Interest interest;

            public static final Logger logger = LogManager.getLogger(TaxDetails.class);

            //logger.debug("Entereing taxDetails in order to print the req format ");


            public PayeeInfo getPayeeInfo() {
                return payeeInfo;
            }

            public void setPayeeInfo(PayeeInfo payeeInfo) {
                this.payeeInfo = payeeInfo;
            }

            public double getTaxDue() {
                return taxDue;
            }

            public void setTaxDue(double taxDue) {
                this.taxDue = taxDue;
            }

            public double getStcgTax() {
                return stcgTax;
            }

            public void setStcgTax(double stcgTax) {
                this.stcgTax = stcgTax;
            }

            public double getLtcgTax() {
                return ltcgTax;
            }

            public void setLtcgTax(double ltcgTax) {
                this.ltcgTax = ltcgTax;
            }

            public double getHECess() {
                return HECess;
            }

            public void setHECess(double HECess) {
                this.HECess = HECess;
            }

            public double getTDS() {
                return TDS;
            }

            public void setTDS(double TDS) {
                this.TDS = TDS;
            }

            public double getAdvnTax() {
                return advnTax;
            }

            public void setAdvnTax(double advnTax) {
                this.advnTax = advnTax;
            }

            public Interest getInterest() {
                return interest;
            }

            public void setInterest(Interest interest) {
                this.interest = interest;
            }

            public double getTaxPayable() {
                return getTotalTax() + surcharge.getSurcharge() - getTDS() + getHECess() - getAdvnTax(); //+getInterest();

            }

            public void setTaxPayable(double taxPayable) {
                this.taxPayable = taxPayable;
            }

            public double getIncomeTax() {
                return incomeTax;
            }

            public void setIncomeTax(double incomeTax) {
                this.incomeTax = incomeTax;
            }

            public Surcharge getSurcharge() {
                return surcharge;
            }

            public void setSurcharge(Surcharge surgeCharge) {
                this.surcharge = surgeCharge;
            }

            public double getTotalTax() {
                return totalTax;
            }

            public void setTotalTax(double totalTax) {
                this.totalTax = totalTax;
            }

            public RegimeType getRegimeType() {
                return regimeType;
            }

            public void setRegimeType(RegimeType regimeType) {
                this.regimeType = regimeType;
            }

            public double getTaxableIncome() {
                return taxableIncome;
            }

            public void setTaxableIncome(double taxableIncome) {
                this.taxableIncome = taxableIncome;
            }

            public double getIncome() {
                return income;
            }

            public void setIncome(double income) {
                this.income = income;
            }

            public double getIncomeFromInterest() {
                return incomeFromInterest;
            }

            public void setIncomeFromInterest(double incomeFromInterest) {
                this.incomeFromInterest = incomeFromInterest;
            }

            public double getIncomeFromOtherSources() {
                return incomeFromOtherSources;
            }

            public void setIncomeFromOtherSources(double incomeFromOtherSources) {
                this.incomeFromOtherSources = incomeFromOtherSources;

            }

            public double getTaxPayableBeforeInterest() {
                return getTotalTax() + surcharge.getSurcharge() - getTDS() + getHECess() ;
            }

            public void setTaxPayableBeforeInterest(double taxPayableBeforeInterest) {
                this.taxPayableBeforeInterest = taxPayableBeforeInterest;
            }

            private static final DecimalFormat format;

            static {
                format = new DecimalFormat("##,##,##0.00");
                format.setRoundingMode(RoundingMode.HALF_UP);
            }

            private String fmt(double value) {
                return format.format(
                        Double.valueOf(value)
                );
            }


            @Override
            public String toString() {
                int width = 20;
                int width1 = 50;

                String pName = payeeInfo.getName();

                String rawPan = payeeInfo.getPanNumber();
                String pPan = (rawPan != null && rawPan.length() >= 6)
                        ? "XXXXXX" + rawPan.substring(6)
                        : rawPan;

                String line = "----------------";
                String empty = "";
                int rebateForInterest = 10000;

                return String.format(
                        "%-" + width + "s %s\n" +
                                "%-" + width + "s %s\n" +
                                "%-" + width + "s %s\n" +
                                "\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n" +
                                "%-" + width1 + "s %s\n",

                        "Name: ", pName,
                        "PAN: ", pPan,
                        "Regime: ", getRegimeType(),
                        "Income: ", fmt(getIncome()),
                        "IncomeFromInterest: ", fmt(getIncomeFromInterest()),
                        "Rebate for Interest: ", fmt(rebateForInterest),
                        empty, line,
                        "Total Income: ", fmt(getIncome() + getIncomeFromInterest() - rebateForInterest),
                        "IncomeFromOtherSources: ", fmt(getIncomeFromOtherSources()),
                        empty, line,
                        "Total Income: ", fmt(getIncome() + getIncomeFromInterest() + getIncomeFromOtherSources() - rebateForInterest),
                        "Taxable Income(After Standard Deduction): ", fmt(taxableIncome),
                        "Tax Due:", fmt(incomeTax),
                        "Short Term Capital Gain:", fmt(getStcgTax()),
                        empty, line,
                        "Total stcg due", fmt(getStcgTax()),
                        "Long Term Capital Gain:", fmt(getLtcgTax()),
                        empty, line,
                        "Total Tax:", fmt(getTotalTax()),
                        "Surcharge (" + surcharge.getRate() + " %):", fmt(surcharge.getSurcharge()),
                        empty, line,
                        empty, fmt(getTotalTax() + surcharge.getSurcharge()),
                        "HE Cess (@4%):", fmt(getHECess()),
                        empty, line,
                        empty, fmt(getTotalTax() + surcharge.getSurcharge() + getHECess()),
                        "TDS:", fmt(getTDS()),
                        empty, line,
                        empty, fmt(getTotalTax() + surcharge.getSurcharge() + getHECess() - getTDS()),
                        "Advance Tax:", fmt(getAdvnTax()),
                        empty, line,
                        empty, fmt(getTaxPayableBeforeInterest()-getAdvnTax()),
                        "Interest from 234A: ", fmt(interest.getInterest234A()),
                        "Interest from 234B: ", fmt(interest.getInterest234B()),
                        "Interest from 234C: ", fmt(interest.getInterest234C()),
                        "Interest:", fmt(interest.getTotalIntereast()),
                        empty, line,
                        empty, fmt(getTaxPayable()+interest.getInterest234A()+interest.getInterest234B()+interest.getInterest234C()),
                        "Tax Payable:", fmt(getTaxPayable()+interest.getInterest234A()+interest.getInterest234B()+interest.getInterest234C())
                        //"Income Tax:", incomeTax

                        //logger.debug("Exiting taxDetails after printing the req format ");

                );
            }
        }