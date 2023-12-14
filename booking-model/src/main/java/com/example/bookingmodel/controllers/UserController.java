package com.example.bookingmodel.controllers;

import com.example.bookingmodel.data.dto.ApartmentsoverviewDto;
import com.example.bookingmodel.data.dto.BinaryContentDto;
import com.example.bookingmodel.data.dto.CustomerDto;
import com.example.bookingmodel.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


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

    @PostMapping("/photo")
    public ResponseEntity<Void> uploadUserPhoto(@RequestParam("id") int userId,
                                                @RequestParam("photo") MultipartFile photo) throws IOException {
        byte[] photoBytes = photo.getBytes();
        String fileName = photo.getOriginalFilename();
        String fileExtension = Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1))
                .orElse("");
        userService.saveUserPhoto(userId, photoBytes, fileName, fileExtension);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get_file_by_user")
    public BinaryContentDto getFileDataByUser(@RequestParam("id") int userId) {
        log.info("Start to find file data...");
        return userService.getFileDataByUser(userId);
    }

    @GetMapping("/get_global_apartment")
    public List<ApartmentsoverviewDto> getGlobalApartment() {
        return userService.getGlobalApartment();
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody CustomerDto userDto) {
        return userService.updateUser(userDto);
    }

}
