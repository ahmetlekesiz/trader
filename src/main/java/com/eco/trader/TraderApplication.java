package com.eco.trader;

import com.eco.trader.util.scraper.YahooFinanceScraper;
import com.eco.trader.util.scraper.runners.DefaultURLTesters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    // localhost:8080/try?exchange=usd
    @RequestMapping(value = "try", method = RequestMethod.GET)
    public String getExchangeTRY(@RequestParam("exchange") String exchange) {
        String url = "";
        if (exchange.equals("usd")) url = "https://finance.yahoo.com/quote/TRY=X";
        else if (exchange.equals("euro")) url = "https://finance.yahoo.com/quote/EURTRY=X";
        long start = System.currentTimeMillis();
        yahooFinanceScraper = new YahooFinanceScraper(url);
        String returnValue = String.valueOf(yahooFinanceScraper.getFinanceRunner().getCurrentValue());
        yahooFinanceScraper.getFinanceRunner().update();
        returnValue = String.valueOf(yahooFinanceScraper.getFinanceRunner().getCurrentValue()) + " " + String.valueOf(yahooFinanceScraper.getFinanceRunner().getChangeAmountAndRatio());
        return returnValue;
    }
}
