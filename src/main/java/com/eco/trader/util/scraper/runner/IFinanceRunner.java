package com.eco.trader.util.scraper.runner;

import org.javatuples.Pair;

public interface IFinanceRunner {
    void update();
    double getCurrentValue();
    Pair<Double, Double> getChangeAmountAndRatio();
    String getFinanceWebURL();
}
