package com.calculator.core.service;

import com.calculator.core.service.interfaces.CalculatorService;
import com.calculator.core.valueobjects.FDBean;
import com.calculator.core.valueobjects.interfaces.InstrumentBean;
import com.calculator.utilities.FDUtilities;

import java.time.LocalDate;
import java.util.*;

public class FDCalculatorService implements CalculatorService {
    private Map<String, Double> monthlyInterestMap = new HashMap<>();
    private List<InstrumentBean> fdList;
    private Map<InstrumentBean, Map<Integer, Double>> financialSummary = new LinkedHashMap<>();

    public Map<InstrumentBean, Map<Integer, Double>> getFinancialSummary() {
        return this.financialSummary;
    }

    public void calculateMaturityAmount(List<InstrumentBean> instruments) {
        for (InstrumentBean instrument : instruments) {
            if (instrument instanceof FDBean) {
                FDBean fd = (FDBean) instrument;
                double interest = 0;
                fd.setFdMaturityDate(this.calculateMaturityDate(fd.getFdOpeningDate(), fd.getFdDays(), fd.getFdMonths(), fd.getFdYears()));
                if (this.isDailyCompounding(fd)) {
                    interest = this.calculateSimpleInterest(fd, fd.getFdOpeningDate(), fd.getFdMaturityDate());
                    fd.setFdInterest(interest);
                    LocalDate financialEndDate = fd.getFdOpeningDate();
                    if (fd.getFdMaturityDate().isAfter(FDUtilities.getFinancialEndDate(financialEndDate))) {
                        this.calculateFinancialSummmary(fd, fd.getFdMaturityDate(), FDUtilities.getFinancialEndDate(financialEndDate), true);
                    }

                    this.calculateFinancialSummmary(fd, fd.getFdMaturityDate(), FDUtilities.getFinancialEndDate(fd.getFdMaturityDate()), false);
                } else {
                    interest = this.calculateQuarterlyInterest(fd);
                }
                fd.setFdMaturityAmount(fd.getFdAmount() + interest);
            }
        }

    }

    private LocalDate calculateMaturityDate(LocalDate inputDate, int days, int months, int years) {
        return inputDate.plusYears(years).plusMonths(months).plusDays(days);
    }

    private boolean isDailyCompounding(FDBean fd) {
        if (fd.getFdYears() == 0) {
            if (fd.getFdMonths() < 6 && fd.getFdDays() <= 180) {
                return true;
            }
            if (fd.getFdMonths() == 6 && fd.getFdDays() == 0) {
                return true;
            }
        }
        return false;
    }

    private double calculateSimpleInterest(FDBean fd, LocalDate startDate, LocalDate endDate) {
        boolean leapYear = false;
        if (startDate.getYear() % 4 == 0) {
            leapYear = true;
        }

        long period = FDUtilities.getNoOfDays(startDate, endDate);
        return (double) Math.round(fd.getFdAmount() * fd.getFdInterestRate() * (double) period / (double) (100 * (leapYear ? 366 : 365)));
    }

    private double calculateQuarterlyInterest(InstrumentBean instrument) {
        if (instrument instanceof FDBean) {
            FDBean fd = (FDBean) instrument;
            double monthlyInterest = 0.0D;
            double simpleInterest = 0.0D;
            double principal = fd.getFdAmount();
            double quarterlyInterest = 0.0D;
            double compoundInterest = 0.0D;
            Map<String, Double> monthlyInterestMap = new LinkedHashMap<>();
            LocalDate nextMonthDate = fd.getFdOpeningDate().plusMonths(1L);
            LocalDate fdStartDate = fd.getFdOpeningDate();
            LocalDate financialEndDate = fd.getFdOpeningDate();
            fd.setFdQuarterDate(fd.getFdOpeningDate());

            while (nextMonthDate.isBefore(fd.getFdMaturityDate().plusDays(1L))) {
                monthlyInterest += this.calculateSimpleInterest(fd, fdStartDate, nextMonthDate);
                if (FDUtilities.isQuarterComplete(fd.getFdQuarterDate(), nextMonthDate)) {
                    quarterlyInterest += monthlyInterest;
                    fd.setFdAmount(principal + quarterlyInterest);
                    monthlyInterest = 0.0D;
                    fd.setFdQuarterDate(nextMonthDate);
                }

                fd.setFdInterest(monthlyInterest + quarterlyInterest);
                monthlyInterestMap.put(nextMonthDate.toString(), fd.getFdInterest());
                if (nextMonthDate.isAfter(FDUtilities.getFinancialEndDate(financialEndDate))) {
                    this.calculateFinancialSummmary(fd, nextMonthDate, FDUtilities.getFinancialEndDate(financialEndDate), true);
                    financialEndDate = nextMonthDate;
                }

                fdStartDate = nextMonthDate;
                nextMonthDate = nextMonthDate.plusMonths(1L);
            }

            compoundInterest = quarterlyInterest + monthlyInterest;
            if (fdStartDate.isBefore(fd.getFdMaturityDate())) {
                simpleInterest = this.calculateSimpleInterest(fd, fdStartDate, fd.getFdMaturityDate());
            }

            fd.setFdAmount(principal);
            fd.setFdInterest(compoundInterest + simpleInterest);
            this.calculateFinancialSummmary(fd, nextMonthDate, FDUtilities.getFinancialEndDate(financialEndDate), false);
            return compoundInterest + simpleInterest;
        } else {
            return 0.0D;
        }
    }

    private void calculateFinancialSummmary(FDBean fd, LocalDate nextMonthDate, LocalDate financialEndDate, boolean calculateInterest) {
        double accumulatedInterest;
        if (calculateInterest) {
            accumulatedInterest = this.calculateSimpleInterest(fd, financialEndDate.plusDays(1L), nextMonthDate);
            double adjustedInterest = fd.getFdInterest() - accumulatedInterest;
            if (this.financialSummary.get(fd) == null) {
                LinkedHashMap<Integer, Double> currentYearInterest = new LinkedHashMap<>();
                currentYearInterest.put(financialEndDate.getYear() - 1, adjustedInterest);
                this.financialSummary.put(fd, currentYearInterest);
            } else {
                accumulatedInterest = 0.0D;

                Integer year;
                for (Iterator var12 = ((Map) this.financialSummary.get(fd)).keySet().iterator(); var12.hasNext(); accumulatedInterest += (Double) ((Map) this.financialSummary.get(fd)).get(year)) {
                    year = (Integer) var12.next();
                }

                ((Map) this.financialSummary.get(fd)).put(financialEndDate.getYear() - 1, adjustedInterest - accumulatedInterest);
            }
        } else if (this.financialSummary.get(fd) == null) {
            HashMap<Integer, Double> currentYearInterest = new HashMap<>();
            currentYearInterest.put(financialEndDate.getYear() - 1, fd.getFdInterest());
            this.financialSummary.put(fd, currentYearInterest);
        } else {
            accumulatedInterest = 0.0D;

            Integer year;
            for (Iterator var8 = ((Map) this.financialSummary.get(fd)).keySet().iterator(); var8.hasNext(); accumulatedInterest += (Double) ((Map) this.financialSummary.get(fd)).get(year)) {
                year = (Integer) var8.next();
            }

            ((Map) this.financialSummary.get(fd)).put(financialEndDate.getYear() - 1, fd.getFdInterest() - accumulatedInterest);
        }

    }

    public Map<String, Double> getMonthlyInterestMap() {
        return this.monthlyInterestMap;
    }

    public void addInstruments(InstrumentBean instrument) {
        if (this.fdList == null) {
            this.fdList = new ArrayList<>();
        }

        this.fdList.add(instrument);
    }

    public List<InstrumentBean> getInstrumentList() {
        return this.fdList;
    }

    public void setInstrumentList(List<InstrumentBean> instruments) {
        this.fdList = instruments;
    }
}
