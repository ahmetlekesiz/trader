package com.eco.trader.util.scraper.runners;

import com.eco.trader.util.url.NotValidYahooFinanceURLException;
import com.eco.trader.util.url.URLNotValidException;
import com.eco.trader.util.url.URLTester;
import org.javatuples.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * A class for creating JSoupRunner objects.
 *
 * <p> While creating a new runner it is likely to get <tt>NotYahooFinanceURLException</tt>
 * or some java.net based Exception like <tt>java.net.MalformedURLException</tt> due to typing
 * not standard web URLs or one with no relation with Yahoo Finance.
 *
 * <p> The class implements base methods from the <tt>IFinanceRunner</tt> since all finance
 * scrapers must implement its methods to be exactly standard and useful.
 *
 * @author Muhammed Bera Koç
 * @see IFinanceRunner
 */

public class JSoupRunner implements IFinanceRunner {
    private final String yahooFinanceWebURL;
    private Document document;

    public JSoupRunner(String yahooFinanceWebURL, URLTester<? extends URLNotValidException> urlTester) {
        try {
            urlTester.testURL(yahooFinanceWebURL);
        } catch (URLNotValidException e) {
            e.printStackTrace();
        }
        this.yahooFinanceWebURL = yahooFinanceWebURL;
        update();
    }

    public String getFinanceWebURL() {
        return yahooFinanceWebURL;
    }

    /**
     * Updates the current document in order to get new values.
     */
    @Override
    public void update() {
        Document currentDocument = null;
        try {
            currentDocument = Jsoup.connect(yahooFinanceWebURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        document = currentDocument;
    }

    /**
     * Gets the current value for the targeted commodity(like stock, currency, etc.).
     * @return A value for the current commodity
     */
    @Override
    public double getCurrentValue() {
        return Double.parseDouble(document.select("#quote-header-info span[data-reactid=\"32\"]").get(0).text());
    }

    /**
     * Gets the change amount and ratio for the targeted commodity(like stock, currency, etc.).
     * @return A tuple of amount and change for the current commodity
     */
    @Override
    public Pair<Double, Double> getChangeAmountAndRatio() {
        String[] rawValue = document.select("#quote-header-info span[data-reactid=\"33\"]").get(0).text().split(" ");
        rawValue[1] = rawValue[1].substring(1, rawValue[1].length() - 2);
        return Pair.fromArray(Arrays.stream(rawValue).map(Double::parseDouble).toArray(Double[]::new));
    }

}
