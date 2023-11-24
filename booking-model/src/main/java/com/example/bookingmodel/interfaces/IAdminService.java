package com.example.bookingmodel.interfaces;

import com.example.bookingmodel.data.dto.CustomerDto;

import java.util.List;

public interface IAdminService {

    List<CustomerDto> findAllUsersByQuery();
}
