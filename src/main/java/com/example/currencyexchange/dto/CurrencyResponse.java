package com.example.currencyexchange.dto;

import lombok.Data;

import java.util.Map;

@Data
public class CurrencyResponse {
    private String base; // Base currency
    private Map<String, Double> rates; // Mapping of target currencies to their rates
}
