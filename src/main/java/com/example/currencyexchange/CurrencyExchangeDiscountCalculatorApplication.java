package com.example.currencyexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CurrencyExchangeDiscountCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeDiscountCalculatorApplication.class, args);
	}

}
