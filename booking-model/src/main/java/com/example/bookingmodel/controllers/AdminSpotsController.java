package com.example.bookingmodel.controllers;

import com.example.bookingmodel.data.dto.*;
import com.example.bookingmodel.interfaces.IAdminSpotsManipulationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController("adminSpotsController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@Tag(name = "Admin controller which handle all operation with spots manipulation")
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

    @PostMapping("/new_global_apartment")
    public String createGlobalApartment(@RequestBody GlobalApartmentDto globalApartmentDto) {
        return adminService.createAddressApartmentProduct(globalApartmentDto.getStreet(), globalApartmentDto.getHouseNumber(),
                globalApartmentDto.getDescription(), globalApartmentDto.getQuantityOfRooms(),
                globalApartmentDto.getAptFree(), globalApartmentDto.getAptSale());
    }

    @GetMapping("/get_favorites")
    public List<FavoriteDto> updateAndGetAllFavoritesApartments() {
        return adminService.updateFavoritesBasedOnHistory();
    }

    @GetMapping("/get_bestsellers")
    public List<BestsellersoverviewDto> getBestsellers() {
        return adminService.getBestsellers();
    }

    @GetMapping("/get_logs")
    public List<LogsHistoryDto> retrieveLogs() {
        log.info("start to retrieve logs...");
        return adminService.retrieveLogs();
    }

    @PostMapping("/get_profit")
    public BigDecimal calculateTotalOrdersAmount(@RequestBody OrderCalculationDto orderCalculationDto) {
        log.info("start to calculate profit...");
        return adminService.calculateTotalOrdersAmount(orderCalculationDto.getCustomerId(),
                orderCalculationDto.getStartDate(), orderCalculationDto.getEndDate());
    }

}
