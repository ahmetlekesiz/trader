package com.eco.trader.util.scraper;

import org.javatuples.Pair;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class YahooFinanceScraperTest {
    private static final String yahooFinanceWebURL = "https://finance.yahoo.com/quote/TRY=X?p=TRY%3DX&.tsrc=fin-srch";
    private static YahooFinanceScraper yahooFinanceScraper;

    @BeforeClass
    public static void init() {
        yahooFinanceScraper = new YahooFinanceScraper(yahooFinanceWebURL);
    }

    @Test
    public void shouldReturnTrueWhenAYahooFinanceURL() {
        assertThat(true, is(equalTo(YahooFinanceScraper.isYahooFinanceURL(yahooFinanceWebURL))));
    }

    @Test(expected = NotYahooFinanceURLException.class)
    public void whenWrongURLGiven_thenItShouldThrowAnException() throws NotYahooFinanceURLException {
        YahooFinanceScraper.testURL("https://www.baeldung.com/");
    }

    @Test
    public void shouldReturnCurrentValue() {
        assertThat(Double.class, is(equalTo(Double.valueOf(yahooFinanceScraper.getCurrentValue()).getClass())));
    }

    @Test
    public void shouldReturnCurrentChangeAmountAndRatio() {
        final Pair<Double, Double> doublePair = new Pair<>(1.0, 1.0);
        assertThat(doublePair.getClass(), is(equalTo(yahooFinanceScraper.getChangeAmountAndRatio().getClass())));
    }

    @Test
    public void shouldReturnConvenientToString() {
        final String expected = String.format("@YahooFinanceScraper%d[rootURL:%s]",
                System.identityHashCode(yahooFinanceScraper), yahooFinanceScraper.getYahooFinanceWebURL());
        assertThat(expected, is(equalTo(yahooFinanceScraper.toString())));
    }
}
