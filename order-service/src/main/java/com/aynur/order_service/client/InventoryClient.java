package com.aynur.order_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class InventoryClient {
    private final WebClient webClient;

    public InventoryClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://inventory-service").build();
    }

    public boolean hasStock(String productId, int qty) {
        return webClient.get()
                .uri("/inventory/" + productId + "?qty=" + qty)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
