package com.example.currencyexchange.service;

import com.example.currencyexchange.dto.CurrencyResponse;
import com.example.currencyexchange.model.Bill;
import com.example.currencyexchange.model.Item;
import com.example.currencyexchange.repository.BillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class CurrencyService {

    private static final String API_URL = "https://open.er-api.com/v6/latest/USD";
    private final BillRepository billRepository;
    private final RestTemplate restTemplate;
    private final UserTypeService userTypeService;
    @Autowired
    public CurrencyService(BillRepository billRepository, RestTemplate restTemplate,
                           UserTypeService userTypeService) {
        this.billRepository = billRepository;
        this.restTemplate = restTemplate;
        this.userTypeService = userTypeService;
    }


    public Map<String, Object> calculateTotalPayable(Bill bill) {
        log.info("Calculating total for bill: {}", bill);
        double total = calculateTotalAmount(bill);
        log.debug("Calculated total amount: {}", total);
        bill.setUserType(userTypeService.getUserTypeById(bill.getUserId()));
        double discount = calculateDiscount(bill, total);
        log.debug("Calculated discount: {}", discount);

        double finalAmount = total - discount;
        log.debug("Final amount after discount: {}", finalAmount);

        double exchangeRate = getExchangeRate(bill.getOriginalCurrency(), bill.getTargetCurrency());
        double convertedAmount = finalAmount * exchangeRate;
        log.debug("Converted amount: {}", convertedAmount);

        // Save bill to the database
        billRepository.save(bill);
        log.info("Saved bill to the database: {}", bill);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("Total Cost", total);
        response.put("Applicable discount", discount);
        response.put("Amount after discount", finalAmount);
        BigDecimal bd = new BigDecimal(convertedAmount);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        response.put("Final Amount in "+bill.getTargetCurrency(), bd.doubleValue());
        return response;
    }

    private double calculateTotalAmount(Bill bill) {
        return bill.getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum();
    }

    private double calculateDiscount(Bill billRequest, double total) {
        double discount = 0;

        // Calculate the total price of non-grocery items
        double nonGroceryTotal = billRequest.getItems().stream()
                .filter(item -> !"groceries".equalsIgnoreCase(item.getCategory()))
                .mapToDouble(Item::getPrice)
                .sum();

        // Calculate percentage discount based on user type and tenure
        double percentageDiscount = 0;
        if (nonGroceryTotal > 0) {  // Check if there are non-grocery items
            if ("employee".equalsIgnoreCase(billRequest.getUserType())) {
                percentageDiscount = 0.30; // 30% discount
            } else if ("affiliate".equalsIgnoreCase(billRequest.getUserType())) {
                percentageDiscount = 0.10; // 10% discount
            } else if (billRequest.getCustomerTenure() > 2) {
                percentageDiscount = 0.05; // 5% discount
            }
        }

        // Apply the percentage discount to non-grocery total
        discount += nonGroceryTotal * percentageDiscount;
        log.debug("Discount after percentage calculation: {}", discount);

        // Apply the $5 discount for every $100 in total
        int flatDiscount = (int) ((total - discount) / 100);
        discount += flatDiscount * 5;
        log.debug("Final discount after applying $5 per $100: {}", discount);

        return discount; // Return the total discount
    }


    @Cacheable(value = "exchangeRates", key = "#baseCurrency + '_' + #targetCurrency")
    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        log.info("Fetching exchange rate for {} to {}", baseCurrency, targetCurrency);
        CurrencyResponse response = restTemplate.getForObject(API_URL, CurrencyResponse.class, baseCurrency);

        // Ensure the response is not null and contains the rates
        if (response != null && response.getRates() != null) {
            Double rate = response.getRates().get(targetCurrency);
            log.debug("Retrieved exchange rate: {}", rate);
            return rate != null ? rate : 0.0; // Handle case if target currency is not found
        }

        log.error("Unable to fetch exchange rate for {} to {}", baseCurrency, targetCurrency);
        throw new RuntimeException("Unable to fetch exchange rate for " + baseCurrency + " to " + targetCurrency);
    }
}
