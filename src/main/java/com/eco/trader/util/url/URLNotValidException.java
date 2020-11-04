package com.eco.trader.util.url;

public class URLNotValidException extends Exception {
    public URLNotValidException() {
        super("Given URL is not viable with pattern.");
    }

    public URLNotValidException(String message) {
        super(message);
    }
}
