package com.example.currencyexchange.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bill_id")
    private List<Item> items;
    private String userType;// "employee", "affiliate", or "customer"
    private Long userId;
    private int customerTenure; // in years
    private String originalCurrency;
    private String targetCurrency;
}
