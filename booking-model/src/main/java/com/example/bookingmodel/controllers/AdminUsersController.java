package com.example.bookingmodel.controllers;

import com.example.bookingmodel.data.dto.*;
import com.example.bookingmodel.interfaces.IAdminUsersManipulationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController("adminUsersController")
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminUsersController {

    private final IAdminUsersManipulationService adminService;

    @GetMapping("/users")
    public List<CustomerDto> getUsresJDBCTemplate() {
        log.info("Start to retrieve all users...");
        return adminService.findAllUsersByQuery();
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        log.info("Start to retrieve all orders...");
        return adminService.findAllOrders();
    }

    @GetMapping("/orders_statistics_by_user")
    public List<OrderDto> getOrdersByUser(@RequestParam int id) {
        log.info("Start to retrieve all orders by user...");
        return adminService.findAllOrdersByUser(id);
    }

    @GetMapping("/customers_overview")
    public List<CustomeroverviewDto> getActiveBookings() {
        log.info("Start to retrieve overview...");
        return adminService.getOverview();
    }

    @GetMapping("/levels_overview")
    public List<LeveloverviewDto> getLevelsOfCustomers() {
        log.info("Start to retrieve levels...");
        return adminService.getLevels();
    }

    @GetMapping("/waitinglist_overview")
    public List<WaitinglistoverviewDto> getWaitngLists() {
        log.info("Start to retrieve waiting lists...");
        return adminService.getWaitngLists();
    }

    @GetMapping("/user_status")
    public String getUserStatus() {
        log.info("Start to retrieve user status...");
        return adminService.getUserStatus();
    }

    @PostMapping("/update_levels")
    public String updateCustomerLevels(@RequestBody @Valid UpdateLevelRequestDto updateLevelRequestDto) {
        log.info("Start to update levels globally...");
        return adminService.updateCustomersLevel(updateLevelRequestDto.getCustomerIds(), updateLevelRequestDto.getNewLevelId());
    }

    @PostMapping("/update_apartments")
    public String updateApartments(@RequestBody @Valid DeleteApartmentsDto deleteApartmentsDto) {
        log.info("Start to update apartments globally...");
        return adminService.deleteApartments(deleteApartmentsDto.getAptsId());
    }

    @PostMapping("/get_customers_history")
    public List<CustomerHistoryDto> getCustomersHistory(@RequestBody @Valid IdentRequestDto identRequestDto) {
        log.info("Start to retrieve user history...");
        return adminService.getUserHistory(identRequestDto.getId());
    }

    @GetMapping("/users_orders_overview")
    public List<OrdersoverviewDto> getOrdersOverview() {
        log.info("Start to retrieve orders overview...");
        return adminService.findOrdersOverview();
    }

    @GetMapping("/levels")
    public List<LevelDto> getAllLevels() {
        log.info("Start to retrieve levels...");
        return adminService.getAllLevels();
    }

    @GetMapping("/rewards")
    public List<RewardDto> getRewards() {
        log.info("Start to retrieve rewards...");
        return adminService.getRewards();
    }

    @GetMapping("/calculate_orders")
    public int calculateOrders() {
        log.info("Start to calculate orders...");
        return adminService.calculateOrders();
    }
}

