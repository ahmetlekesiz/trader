package com.eco.trader.util.scraper;

public class NotYahooFinanceURLException extends Exception {
    public NotYahooFinanceURLException() {
        super("Given URL should be a Yahoo Finance URL.");
    }
}
