package com.eco.trader.util.url;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.function.Supplier;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class URLTesterTest {
    private static class NotValidStackOverFlowURLException extends URLNotValidException {}
    private static URLTester<NotValidStackOverFlowURLException> stackOverFlowURLTester;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init() {
        Pattern stackOverFlowPattern = Pattern.compile("https?://stackoverflow/.+");
        Supplier<NotValidStackOverFlowURLException> exceptionSupplier = NotValidStackOverFlowURLException::new;
        stackOverFlowURLTester = new URLTester<>(stackOverFlowPattern, exceptionSupplier);
    }

    @Test
    public void shouldAnalyseTheGivenURL_ThenReturnFalse() {
        // Arrange
        String randomURL = "https://regex101.com/";
        // Action
        boolean isValidURL = stackOverFlowURLTester.isViableURL(randomURL);
        // Assert
        assertThat(isValidURL, is(equalTo(false)));
    }

    @Test
    public void shouldAnalyseTheGivenURL_ThenReturnTrue() {
        // Arrange
        String randomURL = "https://stackoverflow.com/questions";
        // Action
        boolean isValidURL = stackOverFlowURLTester.isViableURL(randomURL);
        // Assert
        assertThat(isValidURL, is(equalTo(false)));
    }

    @Test
    public void shouldTestTheGivenURL_thenThrowAnException() throws URLNotValidException {
        // Arrange
        String washingtonPostURL = "https://www.washingtonpost.com/";
        // Assert
        expectedException.expect(NotValidStackOverFlowURLException.class);
        // Action
        stackOverFlowURLTester.testURL(washingtonPostURL);
    }
}
