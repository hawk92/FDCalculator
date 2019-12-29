//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.calculator.display.service.interfaces;

import com.calculator.core.valueobjects.interfaces.InstrumentBean;
import java.io.IOException;
import java.util.Map;

public interface PrintingService {
    void printAdvice(Map<InstrumentBean, Map<Integer, Double>> var1) throws IOException;
}
