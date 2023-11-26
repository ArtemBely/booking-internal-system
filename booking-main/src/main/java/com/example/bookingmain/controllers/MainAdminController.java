package com.example.bookingmain.controllers;

import com.example.bookingmodel.data.dto.ProductInformationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("mainAdminController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class MainAdminController {

    @GetMapping("/dashboard")
    public String getHello() {
        return "Hello I'm admin";
    }

//    @PostMapping("/new_product_info")
//    public ProductInformationDto createNewProduct(@RequestBody ProductInformationDto productInformationDto) {
//
//    }
}
