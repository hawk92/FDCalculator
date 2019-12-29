//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.calculator.core.service.interfaces;

import com.calculator.core.valueobjects.interfaces.InstrumentBean;
import java.util.List;
import java.util.Map;

public interface CalculatorService {
    void calculateMaturityAmount(List<InstrumentBean> var1);

    void addInstruments(InstrumentBean var1);

    List<InstrumentBean> getInstrumentList();

    void setInstrumentList(List<InstrumentBean> var1);

    Map<InstrumentBean, Map<Integer, Double>> getFinancialSummary();
}
