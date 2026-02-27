package com.aynur.order_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class InventoryClient {
    private final WebClient webClient;

    public InventoryClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public boolean hasStock(String productId, int qty) {
        Boolean result = webClient.get()
                .uri("http://inventory-service/inventory/{productId}?qty={qty}", productId, qty)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        return Boolean.TRUE.equals(result);
    }
}