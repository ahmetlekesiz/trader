package com.eco.trader.util.scraper;

import com.eco.trader.util.scraper.runners.DefaultURLTesters;
import com.eco.trader.util.scraper.runners.IFinanceRunner;
import com.eco.trader.util.scraper.runners.JSoupRunner;
import com.eco.trader.util.url.NotValidYahooFinanceURLException;
import com.eco.trader.util.url.URLNotValidException;
import com.eco.trader.util.url.URLTester;
import org.jsoup.nodes.Document;

import java.util.regex.Pattern;

/**
 * This class consists of some useful methods for obtaining data
 * from Yahoo Finance.
 *
 * @author Muhammed Bera Koç
 * @see NotValidYahooFinanceURLException
 * @see IFinanceRunner
 */
public class YahooFinanceScraper {
    private final IFinanceRunner financeRunner;

    public YahooFinanceScraper(String yahooFinanceWebURL) {
        financeRunner = new JSoupRunner(yahooFinanceWebURL, DefaultURLTesters.yahooFinanceURLTester);
    }

    public IFinanceRunner getFinanceRunner() {
        return financeRunner;
    }

    @Override
    public String toString() {
        return String.format("@YahooFinanceScraper%d[rootURL:%s]", System.identityHashCode(this), financeRunner.getFinanceWebURL());
    }
}
