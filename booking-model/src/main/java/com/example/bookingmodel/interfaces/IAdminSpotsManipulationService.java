package com.example.bookingmodel.interfaces;

import com.example.bookingmodel.data.dto.*;

import java.util.List;

public interface IAdminSpotsManipulationService {


    ProductInformationDto createNewProduct(ProductInformationDto productInformationDto);

    AddressDto createNewAddress(AddressDto addressDto);

    ApartmentDto createNewApartment(ApartmentDto apartmentDto);

    List<CustomerLevelHistoryDto> customerLevelsHistory();

    List<OrderAuditDto> ordersOverview();

    List<CustomerAddressHistoryDto> customerAddressHistory();
}
