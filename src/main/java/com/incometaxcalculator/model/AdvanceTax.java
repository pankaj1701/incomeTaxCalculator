package com.incometaxcalculator.model;

import java.time.LocalDate;

public class AdvanceTax {
    double advanceTax;
    String advanceTaxPaidDate;

    public AdvanceTax(double advanceTax, String advanceTaxPaidMonth) {

        this.advanceTax = advanceTax;
        this.advanceTaxPaidDate = advanceTaxPaidMonth;
    }

    @Override
    public String toString() {
        return "AdvanceTax{" +
                "advanceTax=" + advanceTax +
                ", advanceTaxPaidDate='" + advanceTaxPaidDate + '\'' +
                '}';
    }

    public double getAdvanceTax() {
        return advanceTax;
    }

    public void setAdvanceTax(double advanceTax) {
        this.advanceTax = advanceTax;
    }

    public LocalDate getAdvanceTaxPaidDate() {
        return LocalDate.parse(advanceTaxPaidDate);
    }

    public void setAdvanceTaxPaidDate(String advanceTaxPaidDate) {
        this.advanceTaxPaidDate = advanceTaxPaidDate;
    }
}
