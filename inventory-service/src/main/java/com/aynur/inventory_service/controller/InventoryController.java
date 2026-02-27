package com.aynur.inventory_service.controller;

import com.aynur.inventory_service.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public boolean hasStock(@PathVariable String productId,
                            @RequestParam int qty) {
        return service.hasStock(productId, qty);
    }
}