package com.example.bookingmodel.interfaces;

import com.example.bookingmodel.data.dto.*;

import java.math.BigDecimal;
import java.util.List;

public interface IAdminSpotsManipulationService {


    ProductInformationDto createNewProduct(ProductInformationDto productInformationDto);

    AddressDto createNewAddress(AddressDto addressDto);

    ApartmentDto createNewApartment(ApartmentDto apartmentDto);

    List<CustomerLevelHistoryDto> customerLevelsHistory();

    List<OrderAuditDto> ordersOverview();

    List<CustomerAddressHistoryDto> customerAddressHistory();

    String createAddressApartmentProduct(String street, int houseNumber, String description, int quantityOfRooms, int aptFree, int aptSale);

    String updateAddressApartmentProduct(String street, String description, int aptFree, double aptSale, int id);

    void deleteApartment(int apartmentId);

    List<FavoriteDto> updateFavoritesBasedOnHistory();

    List<BestsellersoverviewDto> getBestsellers();

    List<LogsHistoryDto> retrieveLogs();

    BigDecimal calculateTotalOrdersAmount(int customerId, String startDate, String endDate);
}
