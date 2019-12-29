package com.calculator.utilities;

import com.calculator.core.constants.FDConstants;
import com.calculator.core.valueobjects.FDBean;
import com.calculator.core.valueobjects.interfaces.InstrumentBean;
import com.calculator.exceptions.CalculationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FDDetailsReader {

    String filepath;

    public FDDetailsReader(String filePath) {
        this.filepath = filePath;
    }

    public List<InstrumentBean> readFDFile() throws CalculationException {
        List<InstrumentBean> fdList = new ArrayList();

        if (filepath == null || filepath.trim().isEmpty()) {
            throw new CalculationException("File path must be specified.Cannot be blank or null");
        }
        try (BufferedReader fdReader = new BufferedReader(new FileReader(new File(filepath)))) {
            String fdData = null;
            //Skip Header
            fdReader.readLine();
            while ((fdData = fdReader.readLine()) != null) {
                FDBean fd = new FDBean();
                StringTokenizer token = new StringTokenizer(fdData, FDConstants.FD_DETAILS_SEPERATOR);
                while (token.hasMoreTokens()) {
                    fd.setFdNumber(token.nextToken());
                    fd.setFdOpeningDate(LocalDate.parse(token.nextToken(), DateTimeFormatter.ISO_DATE));
                    fd.setFdYears(Integer.parseInt(token.nextToken()));
                    fd.setFdMonths(Integer.parseInt(token.nextToken()));
                    fd.setFdDays(Integer.parseInt(token.nextToken()));
                    fd.setFdInterestRate(Double.parseDouble(token.nextToken()));
                    fd.setFdAmount(Double.parseDouble(token.nextToken()));
                    fd.setFdBank(token.nextToken());
                }
                fdList.add(fd);
            }
        } catch (IOException e) {
            throw new CalculationException(e.getMessage());
        }
        return fdList;
    }
}
