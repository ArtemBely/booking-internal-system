package com.example.bookingauth.controllers;

import com.example.bookingauth.services.AuthenticationRequest;
import com.example.bookingauth.services.AuthenticationResponse;
import com.example.bookingauth.services.UserAuthService;
import com.example.bookingmodel.data.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController("userAuthController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/create")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody CustomerDto request) {
        log.info("start of registration process");
        return ResponseEntity.ok(userAuthService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        log.info("start to insert user");
        return ResponseEntity.ok(userAuthService.authenticate(request));
    }

    @PostMapping("/match")
    public boolean matchPass(@Valid @RequestBody CustomerDto customerDTO) {
        log.info("start to insert user");
        return userAuthService.matchPassword(customerDTO);
    }
}
