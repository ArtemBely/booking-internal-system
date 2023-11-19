package com.example.bookingmodel.services;

import com.example.bookingmodel.data.dto.CustomerDto;
import com.example.bookingmodel.data.mapper.CustomerMapper;
import com.example.bookingmodel.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public List<CustomerDto> findAllUsers() {
        log.info("Users finding...");
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::mapToDto)
                .collect(Collectors.toList());
    }
}