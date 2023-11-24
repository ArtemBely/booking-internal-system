package com.example.bookingmain.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("mainUserController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class MainUserController {

    @GetMapping("/dashboard")
    public String getHello() {
        return "Hello I'm user";
    }
}

