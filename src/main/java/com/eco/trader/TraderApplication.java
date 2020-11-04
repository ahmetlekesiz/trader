package com.eco.trader;

import com.eco.trader.util.scraper.YahooFinanceScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TraderApplication {

    private YahooFinanceScraper yahooFinanceScraper;

    public static void main(String[] args) {
        SpringApplication.run(TraderApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello Trader";
    }

    @RequestMapping("/usd")
    public String getUSD() {
        long start = System.currentTimeMillis();
        yahooFinanceScraper = new YahooFinanceScraper("https://finance.yahoo.com/quote/TRY%3DX/community?p=TRY%3DX");
        String returnValue = String.valueOf(yahooFinanceScraper.getCurrentValue());
        // finding the time after the operation is executed
        long end = System.currentTimeMillis();
        //finding the time difference and converting it into seconds
        float sec = (end - start) / 1000F;
        System.out.println(sec + " seconds");
        return returnValue;
    }


}
