package com.aynur.order_service.service;

import com.aynur.order_service.client.InventoryClient;
import com.aynur.order_service.client.PaymentClient;
import com.aynur.order_service.dto.CreateOrderRequest;
import com.aynur.order_service.dto.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;

    public OrderService(InventoryClient inventoryClient,
                        PaymentClient paymentClient) {
        this.inventoryClient = inventoryClient;
        this.paymentClient = paymentClient;
    }

    public OrderResponse createOrder(CreateOrderRequest req) {

        if (!inventoryClient.hasStock(req.getProductId(), req.getQuantity())) {
            return new OrderResponse("REJECTED", "Stock not available");
        }

        String paymentResult = paymentClient.pay(req);
        return new OrderResponse("CREATED", paymentResult);
    }
}