//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.calculator.core.valueobjects;

import com.calculator.core.valueobjects.interfaces.InstrumentBean;
import java.time.LocalDate;

public class FDBean implements InstrumentBean {
    private LocalDate fdOpeningDate;
    private LocalDate fdMaturityDate;
    private LocalDate fdQuarterDate;
    private String fdNumber;
    private String fdBank;
    private double fdAmount;
    private double fdInterestRate;
    private double fdMaturityAmount;
    private double fdInterest;
    private int fdYears;
    private int fdMonths;
    private int fdDays;

    public FDBean() {
    }

    public String toString() {
        return "FDBean [fdOpeningDate=" + this.fdOpeningDate + ", fdMaturityDate=" + this.fdMaturityDate + ", fdAmount=" + this.fdAmount + ", fdInterestRate=" + this.fdInterestRate + ", fdMaturityAmount=" + this.fdMaturityAmount + ", fdInterest=" + this.fdInterest + ", fdYears=" + this.fdYears + ", fdMonths=" + this.fdMonths + ", fdDays=" + this.fdDays + "]";
    }

    public String getFdBank() {
        return this.fdBank;
    }

    public void setFdBank(String fdBank) {
        this.fdBank = fdBank;
    }

    public LocalDate getFdOpeningDate() {
        return this.fdOpeningDate;
    }

    public void setFdOpeningDate(LocalDate fdOpeningDate) {
        this.fdOpeningDate = fdOpeningDate;
    }

    public LocalDate getFdMaturityDate() {
        return this.fdMaturityDate;
    }

    public void setFdMaturityDate(LocalDate fdMaturityDate) {
        this.fdMaturityDate = fdMaturityDate;
    }

    public double getFdAmount() {
        return this.fdAmount;
    }

    public void setFdAmount(double fdAmount) {
        this.fdAmount = fdAmount;
    }

    public double getFdInterestRate() {
        return this.fdInterestRate;
    }

    public void setFdInterestRate(double fdInterestRate) {
        this.fdInterestRate = fdInterestRate;
    }

    public double getFdMaturityAmount() {
        return this.fdMaturityAmount;
    }

    public void setFdMaturityAmount(double fdMaturityAmount) {
        this.fdMaturityAmount = fdMaturityAmount;
    }

    public double getMaturityAmount() {
        return this.fdMaturityAmount;
    }

    public double getFdInterest() {
        return this.fdInterest;
    }

    public void setFdInterest(double fdInterest) {
        this.fdInterest = fdInterest;
    }

    public int getFdYears() {
        return this.fdYears;
    }

    public void setFdYears(int fdYears) {
        this.fdYears = fdYears;
    }

    public int getFdMonths() {
        return this.fdMonths;
    }

    public void setFdMonths(int fdMonths) {
        this.fdMonths = fdMonths;
    }

    public int getFdDays() {
        return this.fdDays;
    }

    public void setFdDays(int fdDays) {
        this.fdDays = fdDays;
    }

    public LocalDate getFdQuarterDate() {
        return this.fdQuarterDate;
    }

    public int hashCode() {
        boolean prime = true;
        int result = 1;
        result = 31 * result + (this.fdBank == null ? 0 : this.fdBank.hashCode());
        result = 31 * result + (this.fdNumber == null ? 0 : this.fdNumber.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            FDBean other = (FDBean)obj;
            if (this.fdBank == null) {
                if (other.fdBank != null) {
                    return false;
                }
            } else if (!this.fdBank.equals(other.fdBank)) {
                return false;
            }

            if (this.fdNumber == null) {
                if (other.fdNumber != null) {
                    return false;
                }
            } else if (!this.fdNumber.equals(other.fdNumber)) {
                return false;
            }

            return true;
        }
    }

    public void setFdQuarterDate(LocalDate fdQuarterDate) {
        this.fdQuarterDate = fdQuarterDate;
    }

    public String getFdNumber() {
        return this.fdNumber;
    }

    public void setFdNumber(String fdNumber) {
        this.fdNumber = fdNumber;
    }
}
