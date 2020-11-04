package com.eco.trader.util.url;

public class NotValidYahooFinanceURLException extends URLNotValidException {
    public NotValidYahooFinanceURLException() {
        super("Given URL should be a Yahoo Finance URL.");
    }
}
