package com.food.phat.controller;

import com.food.phat.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/notifications")
public class SmsController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private JavaMailSender JavaMailSender;
    @GetMapping(value = "/email/{restaurantId}")
    public ResponseEntity<String> sendEmail(@PathVariable Integer restaurantId) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("2251052089phat@ou.edu.vn", "letanphat15122004@gmail.com");
//        message.setSubject("he thong chiu tai 100k user");
//        message.setText("test he thong chiu tai 100k user phat food delivery");
//        JavaMailSender.send(message);
        notificationService.getSubscriptedEmail(restaurantId);

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }

}
//EM5ZBUZN5C1YKDF53UDY25MN