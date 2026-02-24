package com.aynur.payment_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @PostMapping("/payments")
    public String pay() {
        return "PAID";
    }

    @GetMapping("/payments/slow")
    public String slow() throws InterruptedException {
        Thread.sleep(7000);
        return "PAID_SLOW";
    }

    @GetMapping("/payments/fail")
    public String fail() {
        throw new RuntimeException("Payment failed");
    }
}