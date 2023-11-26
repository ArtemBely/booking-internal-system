package com.example.bookingmodel.controllers;

import com.example.bookingmodel.data.dto.*;
import com.example.bookingmodel.interfaces.IAdminSpotsManipulationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController("adminSpotsController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminSpotsController {

    private final IAdminSpotsManipulationService adminService;


    @PostMapping("/new_product_info")
    public ProductInformationDto createNewProduct(@RequestBody @Valid ProductInformationDto productInformationDto) {
        return adminService.createNewProduct(productInformationDto);
    }

    @PostMapping("/new_address_info")
    public AddressDto createNewAddress(@RequestBody @Valid AddressDto addressDto) {
        return adminService.createNewAddress(addressDto);
    }

    @PostMapping("/new_apartment_info")
    public ApartmentDto createNewApartment(@RequestBody @Valid ApartmentDto apartmentDto) {
        return adminService.createNewApartment(apartmentDto);
    }

    @GetMapping("/levels_history")
    public List<CustomerLevelHistoryDto> customerLevelsHistory() {
        return adminService.customerLevelsHistory();
    }

    @GetMapping("/orders_overview")
    public List<OrderAuditDto> ordersOverview() {
        return adminService.ordersOverview();
    }

    @GetMapping("/address_overview")
    public List<CustomerAddressHistoryDto> customerAddressHistory() {
        return adminService.customerAddressHistory();
    }

}
