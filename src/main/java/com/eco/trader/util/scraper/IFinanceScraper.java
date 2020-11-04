package com.eco.trader.util.scraper;


import org.javatuples.Pair;

public interface IFinanceScraper {
    void update();
    double getCurrentValue();
    Pair<Double, Double> getChangeAmountAndRatio();
}
