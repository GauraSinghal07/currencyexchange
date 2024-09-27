package com.example.currencyexchange.repository;

import com.example.currencyexchange.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
