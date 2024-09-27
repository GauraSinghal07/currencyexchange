package com.example.currencyexchange.service;

import com.example.currencyexchange.dto.CurrencyResponse;
import com.example.currencyexchange.model.Bill;
import com.example.currencyexchange.model.Item;
import com.example.currencyexchange.repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CurrencyServiceTest {

    @Mock
    private BillRepository billRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateTotal() {
        // Given
        Bill bill = new Bill();
        bill.setItems(Arrays.asList(
                new Item(1L, "item1", "electronics", 200),
                new Item(2L, "item2", "groceries", 50)
        ));
        bill.setUserType("employee");
        bill.setCustomerTenure(3);
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("EUR");

        CurrencyResponse currencyResponse = new CurrencyResponse();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.85);
        currencyResponse.setRates(rates);

        // Mock the exchange rate retrieval
        when(restTemplate.getForObject(anyString(), eq(CurrencyResponse.class), anyString()))
                .thenReturn(currencyResponse);

        // When calculating the total
        Map<String, Object> result = currencyService.calculateTotal(bill);

        // Then
        assertEquals(250.0, result.get("total"));
        assertEquals(70.0, result.get("discount")); // $5 discount for every $100
        assertEquals(180.0, result.get("finalAmount")); // Total - Discount

        // Verify that the bill was saved
        verify(billRepository).save(bill);
    }

    @Test
    void testGetExchangeRate() {
        // Mock behavior for exchange rate
        CurrencyResponse response = new CurrencyResponse();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.85);
        response.setRates(rates);

        when(restTemplate.getForObject(anyString(), eq(CurrencyResponse.class), anyString()))
                .thenReturn(response);

        // When getting the exchange rate
        double exchangeRate = currencyService.getExchangeRate("USD", "EUR");

        // Then
        assertEquals(0.85, exchangeRate); // Corrected expected value
    }
}
