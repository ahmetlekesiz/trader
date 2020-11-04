package com.eco.trader.util.url;

import java.util.regex.Pattern;

public class URLTesterStore {

    public final static URLTester<URLNotValidException> yahooFinanceURLTester =
            new URLTester<>(Pattern.compile("https?://finance\\.yahoo\\.com/quote/.+"), NotValidYahooFinanceURLException::new);
}
