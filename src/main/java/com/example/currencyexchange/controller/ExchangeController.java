package com.example.currencyexchange.controller;

import com.example.currencyexchange.model.Bill;
import com.example.currencyexchange.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
public class ExchangeController {

    private final CurrencyService currencyService;

    public ExchangeController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculatePayable(@RequestBody Bill bill) {
        log.info("Calculating payable for bill: {}", bill);
        Map<String, Object> response = currencyService.calculateTotal(bill);
        log.info("Calculated payable response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/exchangeRates")
    public ResponseEntity<Double> getExchangeRate(@RequestParam String baseCurrency, @RequestParam String targetCurrency) {
        log.info("Fetching exchange rate from {} to {}", baseCurrency, targetCurrency);
        double exchangeRate = currencyService.getExchangeRate(baseCurrency, targetCurrency);
        log.info("Fetched exchange rate: {}", exchangeRate);
        return new ResponseEntity<>(exchangeRate, HttpStatus.OK);
    }
}
