package com.example.bookingauth.controllers;

import com.example.bookingauth.data.dto.SimulateRequestDto;
import com.example.bookingauth.services.AuthenticationResponse;
import com.example.bookingauth.services.JWTService;
import com.example.bookingmodel.data.mapper.RoleMapper;
import com.example.bookingmodel.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.stream.Collectors;

@Slf4j
@RestController("adminSimulateController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminSimulateController {

    private final JWTService jwtService;

    private final UserDetailsService userDetailsService;

    private final CustomerRepository customerRepository;

    private final RoleMapper roleMapper;

    @PostMapping("/impersonate")
    public ResponseEntity<AuthenticationResponse> impersonate(@Valid @RequestBody SimulateRequestDto simulateRequest, Principal principal) {
        UserDetails adminUserDetails = userDetailsService.loadUserByUsername(principal.getName());
        var user = customerRepository.findCustomerEntitiesByEmail(simulateRequest.getEmail())
                .orElseThrow();
        String token = jwtService.generateImpersonationToken(simulateRequest.getEmail(), adminUserDetails);
        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .token(token)
                        .id(user.getId())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .contentId(user.getContentId())
                        .levelId(user.getLevelId())
                        .dateOfBirth(user.getDateOfBirth())
                        .phone(user.getPhone())
                        .email(user.getEmail())
                        .roles(user.getRoles().stream()
                                .map(roleMapper::mapToDto)
                                .collect(Collectors.toList()))
                        .build()
        );
    }
}
