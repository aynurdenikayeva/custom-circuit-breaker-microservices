package com.aynur.inventory_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @GetMapping("/inventory/{productId}")
    public boolean hasStock(@PathVariable String productId,
                            @RequestParam int qty) {

        return productId.equals("P1") || productId.equals("P2");
    }
}