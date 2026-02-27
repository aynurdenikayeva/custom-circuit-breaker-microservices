package com.aynur.inventory_service.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InventoryService {
    private final Map<String, Integer> stock = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        stock.put("1", 10);
        stock.put("2", 0);
        stock.put("3", 5);
    }

    public boolean hasStock(String productId, int qty) {
        if (qty <= 0) return false;
        Integer available = stock.get(productId);
        return available != null && available >= qty;
    }
}