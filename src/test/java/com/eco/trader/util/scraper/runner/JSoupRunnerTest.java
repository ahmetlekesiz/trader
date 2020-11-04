package com.eco.trader.util.scraper.runner;

import com.eco.trader.util.url.URLTesterStore;
import org.javatuples.Pair;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class JSoupRunnerTest {
    private static final String yahooFinanceWebURL = "https://finance.yahoo.com/quote/TRY=X?p=TRY%3DX&.tsrc=fin-srch";
    private static JSoupRunner jSoupRunner;

    @BeforeClass
    public static void init() {
        jSoupRunner = new JSoupRunner(yahooFinanceWebURL, URLTesterStore.yahooFinanceURLTester);
    }

    @Test
    public void shouldReturnCurrentValue() {
        // Action
        Class<? extends Double> expectedClass = Double.valueOf(jSoupRunner.getCurrentValue()).getClass();
        // Assert
        assertThat(Double.class, is(equalTo(expectedClass)));
    }

    @Test
    public void shouldReturnCurrentChangeAmountAndRatio() {
        // Arrange
        final Pair<Double, Double> doublePair = new Pair<>(1.0, 1.0);
        // Action
        Class<? extends Pair> expectedClass = jSoupRunner.getChangeAmountAndRatio().getClass();
        // Assert
        assertThat(doublePair.getClass(), is(equalTo(expectedClass)));
    }

    @Test
    public void shouldReturnFinanceWebURL() {
        // Action
        String jSoupRunnerFinanceWebURLWebURL = jSoupRunner.getFinanceWebURL();
        // Assert
        assertThat(yahooFinanceWebURL, is(equalTo(jSoupRunnerFinanceWebURLWebURL)));
    }
}
