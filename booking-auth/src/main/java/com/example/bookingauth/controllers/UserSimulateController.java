package com.example.bookingauth.controllers;

import com.example.bookingauth.services.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController("userSimulateController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserSimulateController {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/switch_admin")
    public ResponseEntity<String> backToAdmin(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization").substring("Bearer ".length());
        String userEmail = jwtService.extractUsername(authToken);
        UserDetails adminUserDetails = userDetailsService.loadUserByUsername(userEmail);
        String newToken = jwtService.generateToken(adminUserDetails);

        return ResponseEntity.ok(newToken);
    }
}
