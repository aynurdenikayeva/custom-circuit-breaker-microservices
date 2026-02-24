package com.aynur.api_gateway.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/payment")
    public ResponseEntity<?> paymentFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                        "message", "Payment service temporarily unavailable",
                        "timestamp", Instant.now().toString()
                ));
    }
    @GetMapping("/order")
    public ResponseEntity<?> orderFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                        "message", "Order service temporarily unavailable",
                        "timestamp", Instant.now().toString()
                ));
    }
}