package com.example.FinanceApp.model;

import java.time.LocalDate;

public class FinancialReport {
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalIncome;
    private double totalExpenses;
    private double netIncome;

    public FinancialReport(LocalDate startDate2, LocalDate endDate2, double totalIncome, double totalExpenses, double netIncome) {
        this.startDate = startDate2;
        this.endDate = endDate2;
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.netIncome = netIncome;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(double netIncome) {
        this.netIncome = netIncome;
    }
}

