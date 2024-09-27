package com.example.currencyexchange.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Map;

@Data
@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {
    @Id
    private String baseCurrency;

    @ElementCollection
    @CollectionTable(name = "exchange_rate_map", joinColumns = @JoinColumn(name = "base_currency"))
    @MapKeyColumn(name = "target_currency")
    @Column(name = "rate")
    private Map<String, Double> rates;
}
