//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.calculator.display.service;

import com.calculator.core.valueobjects.FDBean;
import com.calculator.core.valueobjects.interfaces.InstrumentBean;
import com.calculator.display.service.interfaces.PrintingService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class FDPrintingService implements PrintingService {
    private BufferedWriter filePrinter;

    public FDPrintingService() {
        try {
            this.filePrinter = new BufferedWriter(new FileWriter(new File("D:\\FDAdvice\\FD_Details_" + LocalDate.now().getYear() + ".txt")));
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void printAdvice(Map<InstrumentBean, Map<Integer, Double>> instrFinancialSummary) throws IOException {
        Iterator var3 = instrFinancialSummary.keySet().iterator();

        while(var3.hasNext()) {
            InstrumentBean instrument = (InstrumentBean)var3.next();
            FDBean fd = (FDBean)instrument;
            this.filePrinter.write("Bank\t \t\t" + fd.getFdBank());
            this.filePrinter.newLine();
            this.filePrinter.write("FD Number\t \t" + fd.getFdNumber());
            this.filePrinter.newLine();
            this.filePrinter.write("FD Opening Date\t \t" + fd.getFdOpeningDate());
            this.filePrinter.newLine();
            this.filePrinter.write("FD Maturity Date \t" + fd.getFdMaturityDate());
            this.filePrinter.newLine();
            this.filePrinter.write("FD Interest Rate\t" + fd.getFdInterestRate());
            this.filePrinter.newLine();
            this.filePrinter.write("FD Amount\t\t" + fd.getFdAmount());
            this.filePrinter.newLine();
            this.filePrinter.write("FD Maturity Amount\t" + fd.getFdMaturityAmount());
            this.filePrinter.newLine();
            this.filePrinter.write("FD Interest Earned\t" + fd.getFdInterest());
            this.filePrinter.newLine();
            this.filePrinter.write("FD Period\t\t" + (fd.getFdYears() > 1 ? fd.getFdYears() + " years " : fd.getFdYears() + " year ") + (fd.getFdMonths() > 1 ? fd.getFdMonths() + " months " : fd.getFdMonths() + " month ") + (fd.getFdDays() > 1 ? fd.getFdDays() + " days " : fd.getFdDays() + " day "));
            this.filePrinter.newLine();
            this.filePrinter.write("================================================");
            this.filePrinter.newLine();
        }

        this.filePrinter.flush();
        this.printFinancialYearSummary(instrFinancialSummary);
        this.printBankWiseYearlyInterest(instrFinancialSummary);
        this.printFDWiseYearlyInterest(instrFinancialSummary);
    }

    private void printBankWiseYearlyInterest(Map<InstrumentBean, Map<Integer, Double>> instrFinancialSummary) throws IOException {
        double bankWiseYearlyInterest = 0.0D;
        Map<String, Map<Integer, Double>> bankWiseYearlyInterestMap = new HashMap();
        Iterator var6 = instrFinancialSummary.keySet().iterator();

        while(var6.hasNext()) {
            InstrumentBean instrument = (InstrumentBean)var6.next();
            FDBean fd = (FDBean)instrument;
            if (bankWiseYearlyInterestMap.get(fd.getFdBank()) == null) {
                Map<Integer, Double> yearlyInterestMap = new LinkedHashMap();
                bankWiseYearlyInterestMap.put(fd.getFdBank(), yearlyInterestMap);
            }

            Integer year;
            for(Iterator var9 = ((Map)instrFinancialSummary.get(fd)).keySet().iterator(); var9.hasNext(); ((Map)bankWiseYearlyInterestMap.get(fd.getFdBank())).put(year, bankWiseYearlyInterest)) {
                year = (Integer)var9.next();
                if (((Map)bankWiseYearlyInterestMap.get(fd.getFdBank())).get(year) == null) {
                    bankWiseYearlyInterest = (Double)((Map)instrFinancialSummary.get(fd)).get(year);
                } else {
                    bankWiseYearlyInterest = (Double)((Map)bankWiseYearlyInterestMap.get(fd.getFdBank())).get(year) + (Double)((Map)instrFinancialSummary.get(fd)).get(year);
                }
            }
        }

        this.filePrinter.write("Financial Year\t\tTotal Interest\tBank");
        this.filePrinter.newLine();
        var6 = bankWiseYearlyInterestMap.keySet().iterator();

        while(var6.hasNext()) {
            String bankCode = (String)var6.next();
            Iterator var13 = ((Map)bankWiseYearlyInterestMap.get(bankCode)).keySet().iterator();

            while(var13.hasNext()) {
                Integer year = (Integer)var13.next();
                this.filePrinter.write(year + "-" + (year + 1));
                this.filePrinter.write("\t\t" + ((Map)bankWiseYearlyInterestMap.get(bankCode)).get(year));
                this.filePrinter.write("\t\t" + bankCode);
                this.filePrinter.newLine();
            }
        }

        this.filePrinter.write("================================================");
        this.filePrinter.newLine();
        this.filePrinter.flush();
    }

    private void printFDWiseYearlyInterest(Map<InstrumentBean, Map<Integer, Double>> instrFinancialSummary) throws IOException {
        double fdYearlyInterest = 0.0D;
        Map<String, Map<Integer, Double>> fdWiseYearlyInterestMap = new HashMap();
        Iterator var6 = instrFinancialSummary.keySet().iterator();

        while(var6.hasNext()) {
            InstrumentBean instrument = (InstrumentBean)var6.next();
            FDBean fd = (FDBean)instrument;
            if (fdWiseYearlyInterestMap.get(fd.getFdNumber()) == null) {
                Map<Integer, Double> yearlyInterestMap = new LinkedHashMap();
                fdWiseYearlyInterestMap.put(fd.getFdNumber(), yearlyInterestMap);
            }

            Integer year;
            for(Iterator var9 = ((Map)instrFinancialSummary.get(fd)).keySet().iterator(); var9.hasNext(); ((Map)fdWiseYearlyInterestMap.get(fd.getFdNumber())).put(year, fdYearlyInterest)) {
                year = (Integer)var9.next();
                if (((Map)fdWiseYearlyInterestMap.get(fd.getFdNumber())).get(year) == null) {
                    fdYearlyInterest = (Double)((Map)instrFinancialSummary.get(fd)).get(year);
                } else {
                    fdYearlyInterest = (Double)((Map)fdWiseYearlyInterestMap.get(fd.getFdNumber())).get(year) + (Double)((Map)instrFinancialSummary.get(fd)).get(year);
                }
            }
        }

        this.filePrinter.write("Financial Year\t\tTotal Interest\tFD Number");
        this.filePrinter.newLine();
        var6 = fdWiseYearlyInterestMap.keySet().iterator();

        while(var6.hasNext()) {
            String fdNumber = (String)var6.next();
            Iterator var13 = ((Map)fdWiseYearlyInterestMap.get(fdNumber)).keySet().iterator();

            while(var13.hasNext()) {
                Integer year = (Integer)var13.next();
                this.filePrinter.write(year + "-" + (year + 1));
                this.filePrinter.write("\t\t" + ((Map)fdWiseYearlyInterestMap.get(fdNumber)).get(year));
                this.filePrinter.write("\t\t" + fdNumber);
                this.filePrinter.newLine();
            }
        }

        this.filePrinter.write("================================================");
        this.filePrinter.newLine();
        this.filePrinter.flush();
    }

    private void printFinancialYearSummary(Map<InstrumentBean, Map<Integer, Double>> instrFinancialSummary) throws IOException {
        Map<String, Double> yearlyInterestMap = new LinkedHashMap();
        double yearlyInterest = 0.0D;
        Iterator var6 = instrFinancialSummary.keySet().iterator();

        while(var6.hasNext()) {
            InstrumentBean instrument = (InstrumentBean)var6.next();
            Map<Integer, Double> financialSummary = (Map)instrFinancialSummary.get(instrument);

            for(Iterator var9 = financialSummary.keySet().iterator(); var9.hasNext(); yearlyInterest = 0.0D) {
                Integer year = (Integer)var9.next();
                yearlyInterest += (Double)financialSummary.get(year);
                if (yearlyInterestMap.get(year + "-" + (year + 1)) == null) {
                    yearlyInterestMap.put(year + "-" + (year + 1), yearlyInterest);
                } else {
                    yearlyInterest += (Double)yearlyInterestMap.get(year + "-" + (year + 1));
                    yearlyInterestMap.put(year + "-" + (year + 1), yearlyInterest);
                }
            }
        }

        this.filePrinter.write("Financial Year\t\tTotal Interest");
        this.filePrinter.newLine();
        var6 = yearlyInterestMap.keySet().iterator();

        while(var6.hasNext()) {
            String year = (String)var6.next();
            this.filePrinter.write(year);
            this.filePrinter.write("\t\t" + yearlyInterestMap.get(year));
            this.filePrinter.newLine();
        }

        this.filePrinter.write("================================================");
        this.filePrinter.newLine();
        this.filePrinter.flush();
    }
}
