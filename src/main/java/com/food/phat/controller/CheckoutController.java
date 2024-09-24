package com.food.phat.controller;

import com.food.phat.dto.PaymentDTO;
import com.food.phat.dto.order.OrderPost;
import com.food.phat.dto.payment.InitPaymentRequest;
import com.food.phat.dto.payment.InitPaymentResponse;
import com.food.phat.dto.payment.IpnResponse;
import com.food.phat.service.Impl.UserService;
import com.food.phat.service.OrderService;
import com.food.phat.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CheckoutController {

    private final PaymentService paymentService;

    @GetMapping("/order/checkout")
    public ResponseEntity<?> checkout(@RequestBody InitPaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.init(paymentRequest), HttpStatus.OK);

    }

    @GetMapping("vnpay_ipn")
    public IpnResponse payCallbackHandler(@RequestParam Map<String, String> params) {
        return paymentService.process(params);
    }
}
