package com.food.phat.controller;

import com.food.phat.dto.payment.InitPaymentRequest;
import com.food.phat.dto.payment.IpnResponse;
import com.food.phat.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CheckoutController {

    private final PaymentService paymentService;

    @GetMapping("/orders/checkout")
    public ResponseEntity<?> checkout(@RequestBody InitPaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.init(paymentRequest), HttpStatus.OK);

    }

    @GetMapping("vnpay_ipn")
    public IpnResponse payCallbackHandler(@RequestParam Map<String, String> params) {
        return paymentService.process(params);
    }
}
