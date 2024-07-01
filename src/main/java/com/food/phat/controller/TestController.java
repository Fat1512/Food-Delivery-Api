package com.food.phat.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {


    @GetMapping("/getting")
    public ResponseEntity<String> getRes() {
        SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("asdd", HttpStatus.OK);
    }
}
