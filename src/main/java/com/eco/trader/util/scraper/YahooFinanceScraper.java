package com.eco.trader.util.scraper;

import org.javatuples.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * This class consists of some useful methods for obtaining data
 * from Yahoo Finance.
 *
 * <p> While creating a new scraper it is likely to get <tt>NotYahooFinanceURLException</tt>
 * or some java.net based Exception like <tt>java.net.MalformedURLException</tt> due to typing
 * not standard web URLs or one with no relation with Yahoo Finance.
 *
 * <p> The class implements base methods from the <tt>IFinanceScraper</tt> since all finance
 * scrapers must implement its methods to be exactly standard and useful.
 *
 * @author Muhammed Bera Koç
 * @see NotYahooFinanceURLException
 * @see IFinanceScraper
 */
public class YahooFinanceScraper implements IFinanceScraper {
    private final String yahooFinanceWebURL;
    private Document document;

    public YahooFinanceScraper(String yahooFinanceWebURL) {
        try {
            testURL(yahooFinanceWebURL);
        } catch (NotYahooFinanceURLException e) {
            e.printStackTrace();
        }
        try {
            testURL(yahooFinanceWebURL);
        } catch (NotYahooFinanceURLException e) {
            e.printStackTrace();
        }
        this.yahooFinanceWebURL = yahooFinanceWebURL;
        update();
    }

    /**
     * Tests the given URL.
     * @param url A URL String
     * @throws NotYahooFinanceURLException If the given URL is not a Yahoo Finance URL.
     */
    public static void testURL(String url) throws NotYahooFinanceURLException {
        if (!isYahooFinanceURL(url)) {
            throw new NotYahooFinanceURLException();
        }
    }

    /**
     * Detects Yahoo Finance URL using Regex pattern.
     * @param url A URL String
     * @return A boolean value indicates whether the given URL is convenient with Yahoo Finance ones or not
     */
    public static boolean isYahooFinanceURL(String url) {
        Pattern yahooFinanceURLPattern = Pattern.compile("https?://finance\\.yahoo\\.com/quote/.+");
        return yahooFinanceURLPattern.matcher(url).find();
    }

    public String getYahooFinanceWebURL() {
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

    @Override
    public String toString() {
        return String.format("@YahooFinanceScraper%d[rootURL:%s]", System.identityHashCode(this), yahooFinanceWebURL);
    }
}
