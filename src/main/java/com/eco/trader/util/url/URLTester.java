package com.eco.trader.util.url;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * A class for testing URLs with given <tt>Pattern</tt>.
 * @param <T> Type of the Exception class
 */
public class URLTester<T extends URLNotValidException> {
    private final Pattern urlPattern;
    private final Supplier<T> exceptionSupplier;

    public URLTester(Pattern urlPattern, Supplier<T> exceptionSupplier) {
        this.urlPattern = urlPattern;
        this.exceptionSupplier = Objects.requireNonNull(exceptionSupplier);
    }

    /**
     * Tests the given URL.
     * @param url A URL String
     * @throws NotValidYahooFinanceURLException If the given URL is not a Yahoo Finance URL.
     */
    public void testURL(String url) throws URLNotValidException {
        if (!isViableURL(url)) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Detects Yahoo Finance URL using Regex pattern.
     * @param url A URL String
     * @return A boolean value indicates whether the given URL is convenient with Yahoo Finance ones or not
     */
    public boolean isViableURL(String url) {
        return urlPattern.matcher(url).find();
    }
}
