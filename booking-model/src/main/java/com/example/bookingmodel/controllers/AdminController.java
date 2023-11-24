package com.example.bookingmodel.controllers;

import com.example.bookingmodel.data.dto.CustomerDto;
import com.example.bookingmodel.interfaces.IAdminService;
import com.example.bookingmodel.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("adminController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final IAdminService adminService;

    @GetMapping("/users")
    public List<CustomerDto> getUsresJDBCTemplate() {
        log.info("Start to retrieve all users...");
        return adminService.findAllUsersByQuery();
    }

}
