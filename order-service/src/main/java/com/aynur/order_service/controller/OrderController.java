package com.aynur.order_service.controller;

import com.aynur.order_service.dto.CreateOrderRequest;
import com.aynur.order_service.dto.OrderResponse;
import com.aynur.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponse create(@RequestBody @Valid CreateOrderRequest req) {
        return service.createOrder(req);
    }
}
