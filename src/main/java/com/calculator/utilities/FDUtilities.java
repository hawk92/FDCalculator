//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.calculator.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FDUtilities {
    private static int FIN_LAST_MONTH = 4;
    private static int FIN_LAST_DAY = 1;
    private static LocalDate now = LocalDate.now();

    public FDUtilities() {
    }

    public static LocalDate getCurrentFinStartDate() {
        return LocalDate.of(now.getYear(), FIN_LAST_MONTH, FIN_LAST_DAY);
    }

    public static LocalDate getCurrentFinEndDate() {
        return LocalDate.of(now.getYear() + 1, FIN_LAST_MONTH, FIN_LAST_DAY);
    }

    public static boolean isDate1greaterThanDate2(LocalDate date1, LocalDate date2) {
        return date1.isAfter(date2);
    }

    public static long getNoOfDays(LocalDate date1, LocalDate date2) {
        return ChronoUnit.DAYS.between(date1, date2);
    }

    public static LocalDate addMonths(LocalDate date1, long noOfMonths) {
        return date1.plusMonths(noOfMonths);
    }

    public static LocalDate convertToDate(String inputDate) {
        return LocalDate.parse(inputDate, DateTimeFormatter.ISO_DATE);
    }

    public static boolean isQuarterComplete(LocalDate date1, LocalDate date2) {
        return date1.plusMonths(3L).equals(date2);
    }

    public static LocalDate getFinancialEndDate(LocalDate date1) {
        return date1.getMonthValue() < 4 ? LocalDate.of(date1.getYear(), FIN_LAST_MONTH, FIN_LAST_DAY) : LocalDate.of(date1.getYear() + 1, FIN_LAST_MONTH, FIN_LAST_DAY);
    }
}
