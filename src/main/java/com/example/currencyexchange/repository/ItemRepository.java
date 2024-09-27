package com.example.currencyexchange.repository;

import com.example.currencyexchange.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
