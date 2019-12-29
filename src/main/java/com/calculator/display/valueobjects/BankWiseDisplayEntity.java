
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.calculator.display.valueobjects;

import com.calculator.display.valueobjects.interfaces.BasicDisplayEntity;

public class BankWiseDisplayEntity implements BasicDisplayEntity {
    private Integer year;
    private String bankCode;
    private Double amount;

    public BankWiseDisplayEntity() {
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.setYear(year);
    }

    public Double getAmount() {
        return this.amount;
    }

    public int hashCode() {
        boolean prime = true;
        int result = 1;
        result = 31 * result + (this.bankCode == null ? 0 : this.bankCode.hashCode());
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
            BankWiseDisplayEntity other = (BankWiseDisplayEntity)obj;
            if (this.bankCode == null) {
                if (other.bankCode != null) {
                    return false;
                }
            } else if (!this.bankCode.equals(other.bankCode)) {
                return false;
            }

            return true;
        }
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
