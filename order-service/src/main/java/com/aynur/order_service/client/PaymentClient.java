package com.aynur.order_service.client;

import com.aynur.order_service.dto.CreateOrderRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PaymentClient {
    private final WebClient webClient;

    public PaymentClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public String pay(CreateOrderRequest req) {
        return webClient.post()
                .uri("http://payment-service/payments")
                .bodyValue(req)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}