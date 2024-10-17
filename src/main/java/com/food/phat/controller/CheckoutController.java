package com.food.phat.controller;

import com.food.phat.dto.MessageResponse;
import com.food.phat.dto.payment.InitPaymentRequest;
import com.food.phat.dto.payment.InitPaymentResponse;
import com.food.phat.dto.payment.IpnResponse;
import com.food.phat.service.PaymentService;
import com.food.phat.utils.ApiResponseMessage;
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

    @GetMapping("/orders/{orderId}/checkout")
    public ResponseEntity<MessageResponse> checkout(@RequestBody InitPaymentRequest paymentRequest) {
        InitPaymentResponse initPaymentResponse = paymentService.init(paymentRequest);
        MessageResponse messageResponse = MessageResponse.builder()
                .status(HttpStatus.OK)
                .message(ApiResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .data(initPaymentResponse)
                .build();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }

    @GetMapping("vnpay_ipn")
    public IpnResponse payCallbackHandler(@RequestParam Map<String, String> params) {
        return paymentService.process(params);
    }
}
