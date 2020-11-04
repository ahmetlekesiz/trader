package com.eco.trader.util.scraper;

import com.eco.trader.util.url.URLTester;
import com.eco.trader.util.url.URLTesterStore;
import com.eco.trader.util.scraper.runner.IFinanceRunner;
import com.eco.trader.util.url.NotValidYahooFinanceURLException;

import java.lang.reflect.InvocationTargetException;

/**
 * This class consists of some useful methods for obtaining data
 * from Yahoo Finance.
 *
 * @param <T> Type of the FinanceRunner
 * @author Muhammed Bera Koç
 * @see NotValidYahooFinanceURLException
 * @see IFinanceRunner
 */
public class YahooFinanceScraper<T extends IFinanceRunner> {
    private final IFinanceRunner financeRunner;

    private YahooFinanceScraper(String yahooFinanceWebURL, IFinanceRunner financeRunner) {
        this.financeRunner = financeRunner;
    }

    public static <U extends IFinanceRunner> YahooFinanceScraper<U> createYahooFinanceScraperFromClass(String yahooFinanceWebURL, Class<U> financeRunnerClass) {
        IFinanceRunner financeRunner = null;
        try {
            financeRunner = financeRunnerClass.getConstructor(String.class, URLTester.class).newInstance(yahooFinanceWebURL, URLTesterStore.yahooFinanceURLTester);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new YahooFinanceScraper<>(yahooFinanceWebURL, financeRunner);
    }

    public static <U extends IFinanceRunner> YahooFinanceScraper<U> createYahooFinanceScraperFromObject(String yahooFinanceWebURL, IFinanceRunner financeRunner) {
        return new YahooFinanceScraper<U>(yahooFinanceWebURL, financeRunner);
    }

    public IFinanceRunner getFinanceRunner() {
        return financeRunner;
    }

    @Override
    public String toString() {
        return String.format("@YahooFinanceScraper%d[rootURL:%s]", System.identityHashCode(this), financeRunner.getFinanceWebURL());
    }
}
