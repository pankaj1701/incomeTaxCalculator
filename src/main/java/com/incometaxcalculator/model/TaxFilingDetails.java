package com.incometaxcalculator.model;

import com.incometaxcalculator.common.RegimeType;
import com.incometaxcalculator.exception.TaxCalculatorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaxFilingDetails {

    public PayeeInfo payeeInfo;

    double income;
    double incomeFromInterest;
    double incomeFromOtherSources;
    double taxableIncome;
    RegimeType regimeType = RegimeType.NEW;

    List<AdvanceTax> advanceTax = new ArrayList<>();

    double stcg;
    double ltcg;

    double TDS;
    double totalAdvanceTaxPaid;

    String filingDate;
    String assessmentYear;


    public PayeeInfo getPayeeInfo() {
        return payeeInfo;
    }

    public void setPayeeInfo(PayeeInfo payeeInfo) {
        this.payeeInfo = payeeInfo;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public RegimeType getRegimeType() {
        return regimeType;
    }

    public void setRegimeType(RegimeType regimeType) {
        this.regimeType = regimeType;
    }

    public double getStcg() {
        return stcg;
    }

    public void setStcg(double stcg) throws TaxCalculatorException {
        this.stcg = stcg;
    }

    public double getLtcg() {
        return ltcg;
    }

    public void setLtcg(double ltcg) throws TaxCalculatorException {
        this.ltcg = ltcg;
    }

    public double getTotalIncome() {
        return income + incomeFromInterest + incomeFromOtherSources;
    }

    public double getIncomeFromInterest() {
        return incomeFromInterest;
    }

    public void setIncomeFromInterest(double incomeFromInterest) throws TaxCalculatorException {
        this.incomeFromInterest = incomeFromInterest;
    }

    public double getIncomeFromOtherSources() {
        return incomeFromOtherSources;
    }

    public void setIncomeFromOtherSources(double incomeFromOtherSources) {
        this.incomeFromOtherSources = incomeFromOtherSources;
    }

    public double getTDS() {
        return TDS;
    }

    public void setTDS(double TDS) {
        this.TDS = TDS;
    }

    public double getTotalAdvanceTaxPaid() {
        return totalAdvanceTaxPaid;
    }

    public void setTotalAdvanceTaxPaid(double totalAdvanceTaxPaid) {
        this.totalAdvanceTaxPaid = totalAdvanceTaxPaid;
    }

    public String getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(String filingDate)  {
        this.filingDate = filingDate;
    }

    public List<AdvanceTax> getAdvanceTax() {
        return advanceTax;
    }

    public void setAdvanceTax(List<AdvanceTax> advanceTax) {
        this.advanceTax = advanceTax;
    }

    public String getAssessmentYear() {
        return assessmentYear;
    }

    public void setAssessmentYear(String assessmentYear) {
        this.assessmentYear = assessmentYear;
    }
}