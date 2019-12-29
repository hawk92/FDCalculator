package com.calculator;

import com.calculator.core.service.FDCalculatorService;
import com.calculator.core.service.interfaces.CalculatorService;
import com.calculator.display.service.FDPrintingService;
import com.calculator.exceptions.CalculationException;
import com.calculator.utilities.FDDetailsReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FDCalculator {

    public static void main(String[] args) {
        System.out.println("Enter file path for FD details");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String filePath = br.readLine();
            FDDetailsReader fdReader = new FDDetailsReader(filePath);
            CalculatorService calService = new FDCalculatorService();
            calService.setInstrumentList(fdReader.readFDFile());
            calService.calculateMaturityAmount(calService.getInstrumentList());
            FDPrintingService printingService = new FDPrintingService();
            printingService.printAdvice(calService.getFinancialSummary());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CalculationException e) {
            e.printStackTrace();
        }
    }
}
