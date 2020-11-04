package com.eco.trader.util.scraper.runners;


import org.javatuples.Pair;

public interface IFinanceRunner {
    void update();
    double getCurrentValue();
    Pair<Double, Double> getChangeAmountAndRatio();
    String getFinanceWebURL();
}
