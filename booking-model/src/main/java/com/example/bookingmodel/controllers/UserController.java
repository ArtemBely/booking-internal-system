package com.example.bookingmodel.controllers;

import com.example.bookingmodel.data.dto.ApartmentsoverviewDto;
import com.example.bookingmodel.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController("userController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/birthday_actions")
    public String getBirthdayActions() {
        log.info("Information about birthday...");
        return userService.getBirthdayData();
    }

    @GetMapping("/next_level")
    public String getInfoAboutNextLevel() {
        log.info("Next level...");
        return userService.getNextLevel();
    }

    @GetMapping("/get_global_apartment")
    public List<ApartmentsoverviewDto> getGlobalApartment() {
        return userService.getGlobalApartment();
    }

}
