package com.eco.trader.util.scraper.runners;

import com.eco.trader.util.url.NotValidYahooFinanceURLException;
import com.eco.trader.util.url.URLNotValidException;
import com.eco.trader.util.url.URLTester;

import java.util.regex.Pattern;

public class DefaultURLTesters {

    public final static URLTester<URLNotValidException> yahooFinanceURLTester =
            new URLTester<>(Pattern.compile("https?://finance\\.yahoo\\.com/quote/.+"), NotValidYahooFinanceURLException::new);
}
