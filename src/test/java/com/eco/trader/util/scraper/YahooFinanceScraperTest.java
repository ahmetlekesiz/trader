package com.eco.trader.util.scraper;

import com.eco.trader.util.scraper.runners.DefaultURLTesters;
import com.eco.trader.util.url.NotValidYahooFinanceURLException;
import com.eco.trader.util.url.URLNotValidException;
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
        // Arrange
        boolean expected = DefaultURLTesters.yahooFinanceURLTester.isViableURL(yahooFinanceWebURL);
        // Assert
        assertThat(true, is(equalTo(expected)));
    }

    @Test(expected = NotValidYahooFinanceURLException.class)
    public void whenWrongURLGiven_thenItShouldThrowAnException() throws URLNotValidException {
        // Arrange
        String url = "https://www.baeldung.com/";
        // Assert
        DefaultURLTesters.yahooFinanceURLTester.testURL(url);
    }

    @Test
    public void shouldReturnCurrentValue() {
        // Action
        Class<? extends Double> expectedClass = Double.valueOf(yahooFinanceScraper.getFinanceRunner().getCurrentValue()).getClass();
        // Assert
        assertThat(Double.class, is(equalTo(expectedClass)));
    }

    @Test
    public void shouldReturnCurrentChangeAmountAndRatio() {
        // Arrange
        final Pair<Double, Double> doublePair = new Pair<>(1.0, 1.0);
        // Action
        Class<? extends Pair> expectedClass = yahooFinanceScraper.getFinanceRunner().getChangeAmountAndRatio().getClass();
        // Assert
        assertThat(doublePair.getClass(), is(equalTo(expectedClass)));
    }

    @Test
    public void shouldReturnConvenientToString() {
        // Arrange
        final String expected = String.format("@YahooFinanceScraper%d[rootURL:%s]",
                System.identityHashCode(yahooFinanceScraper), yahooFinanceScraper.getFinanceRunner().getFinanceWebURL());
        // Action
        String toStringValue = yahooFinanceScraper.toString();
        // Assert
        assertThat(expected, is(equalTo(toStringValue)));
    }
}
