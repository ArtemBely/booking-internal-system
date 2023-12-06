package com.example.bookingmodel.interfaces;

import com.example.bookingmodel.data.dto.ApartmentsoverviewDto;
import com.example.bookingmodel.data.dto.CustomerDto;

import java.util.List;

public interface IUserService {

    String getNextLevel();

    String getBirthdayData();

    List<ApartmentsoverviewDto> getGlobalApartment();
}
