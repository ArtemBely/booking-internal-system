package com.example.bookingauth.controllers;

import com.example.bookingauth.data.dto.SimulateRequestDto;
import com.example.bookingauth.services.AuthenticationResponse;
import com.example.bookingauth.services.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@RestController("adminSimulateController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminSimulateController {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/impersonate")
    public ResponseEntity<String> impersonate(@RequestBody @Valid  SimulateRequestDto userEmail, Principal principal) {
        UserDetails adminUserDetails = userDetailsService.loadUserByUsername(principal.getName());
        String token = jwtService.generateImpersonationToken(userEmail.getEmail(), adminUserDetails);
        return ResponseEntity.ok(token);
    }

}
