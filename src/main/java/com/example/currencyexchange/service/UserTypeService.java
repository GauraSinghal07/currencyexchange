package com.example.currencyexchange.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class UserTypeService {
    private static final Map<Long, String> userTypeMap = new HashMap<>();

    static {
        userTypeMap.put(1L, "employee");
        userTypeMap.put(2L, "affiliate");
        userTypeMap.put(3L, "customer");
        // Add more user IDs and types as needed
    }

    public String getUserTypeById(Long userId) {
        return userTypeMap.getOrDefault(userId, "customer"); // Default to "customer"
    }
}

