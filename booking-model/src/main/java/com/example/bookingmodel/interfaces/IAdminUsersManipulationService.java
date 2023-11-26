package com.example.bookingmodel.interfaces;

import com.example.bookingmodel.data.dto.*;
import com.example.bookingmodel.data.entity.Order;

import java.util.List;

public interface IAdminUsersManipulationService {

    List<CustomerDto> findAllUsersByQuery();

    List<OrderDto> findAllOrders();

    List<OrderDto> findAllOrdersByUser(int id);

    List<CustomeroverviewDto> getOverview();

    List<LeveloverviewDto> getLevels();

    List<WaitinglistoverviewDto> getWaitngLists();

    String getUserStatus();

    String updateCustomersLevel(Integer[] customerIds, int newLevelId);

    String deleteApartments(Integer[] apartmentIds);
}
