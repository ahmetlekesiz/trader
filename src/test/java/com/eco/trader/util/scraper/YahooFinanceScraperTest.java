package com.eco.trader.util.scraper;

import com.eco.trader.util.scraper.runner.IFinanceRunner;
import com.eco.trader.util.scraper.runner.JSoupRunner;
import com.eco.trader.util.url.URLTesterStore;
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
    private static YahooFinanceScraper<JSoupRunner> yahooFinanceScraper;

    @BeforeClass
    public static void init() {
        yahooFinanceScraper = YahooFinanceScraper.createYahooFinanceScraperFromClass(yahooFinanceWebURL, JSoupRunner.class);
    }

    @Test
    public void shouldReturnConvenientToStringOutput() {
        // Arrange
        final String expected = String.format("@YahooFinanceScraper%d[rootURL:%s]",
                System.identityHashCode(yahooFinanceScraper), yahooFinanceScraper.getFinanceRunner().getFinanceWebURL());
        // Action
        String toStringValue = yahooFinanceScraper.toString();
        // Assert
        assertThat(expected, is(equalTo(toStringValue)));
    }

    @Test
    public void shouldReturnValidFinanceRunner() {
        // Arrange
        IFinanceRunner expectedFinanceRunner = new JSoupRunner(yahooFinanceWebURL, URLTesterStore.yahooFinanceURLTester);
        YahooFinanceScraper<JSoupRunner> yahooFinanceScraperObject =
                YahooFinanceScraper.createYahooFinanceScraperFromObject(yahooFinanceWebURL, expectedFinanceRunner);
        // Action
        IFinanceRunner actualFinanceRunner = yahooFinanceScraperObject.getFinanceRunner();
        // Assert
        assertThat(expectedFinanceRunner, is(equalTo(actualFinanceRunner)));
    }
}
