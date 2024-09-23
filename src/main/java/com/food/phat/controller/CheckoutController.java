package com.food.phat.controller;

import com.food.phat.dto.PaymentDTO;
import com.food.phat.dto.order.OrderPost;
import com.food.phat.dto.payment.InitPaymentRequest;
import com.food.phat.dto.payment.InitPaymentResponse;
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

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CheckoutController {

    private final PaymentService paymentService;

    @GetMapping("/order/checkout")
    public ResponseEntity<InitPaymentResponse> checkout(@RequestBody InitPaymentRequest request) {
        return new ResponseEntity<>(paymentService.init(request), HttpStatus.OK);
    }

    @GetMapping("/vnpay_ipn")
    public ResponseEntity<String> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }
}
