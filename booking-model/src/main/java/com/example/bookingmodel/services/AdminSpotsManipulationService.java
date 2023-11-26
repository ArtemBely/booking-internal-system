package com.example.bookingmodel.services;

import com.example.bookingmodel.data.dto.*;
import com.example.bookingmodel.data.entity.CustomerAddressHistory;
import com.example.bookingmodel.data.entity.CustomerLevelHistory;
import com.example.bookingmodel.data.entity.OrderAudit;
import com.example.bookingmodel.data.entity.Waitinglistoverview;
import com.example.bookingmodel.data.mapper.AddressHistoryMapper;
import com.example.bookingmodel.data.mapper.LevelHistoryMapper;
import com.example.bookingmodel.data.mapper.OrderAuditMapper;
import com.example.bookingmodel.interfaces.IAdminSpotsManipulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminSpotsManipulationService implements IAdminSpotsManipulationService {


    private final JdbcTemplate jdbcTemplate;

    private final LevelHistoryMapper levelHistoryMapper;

    private final OrderAuditMapper orderAuditMapper;

    private final AddressHistoryMapper addressHistoryMapper;

    private String insertProductInformation, insertAddressInformation, insertApartmentInformation,
            orderAudit, addressHistory, levelsHistory;

    @Autowired
    public AdminSpotsManipulationService(JdbcTemplate jdbcTemplate,
                                         @Value("${sql.insertProductInformation}") String insertProductInformation,
                                         @Value("${sql.insertAddress}") String insertAddressInformation,
                                         @Value("${sql.insertApartment}") String insertApartmentInformation,
                                         @Value("${sql.addressHistory}") String addressHistory,
                                         @Value("${sql.orderAudit}") String orderAudit,
                                         @Value("${sql.levelsHistory}") String levelsHistory,
                                         LevelHistoryMapper levelHistoryMapper,
                                         OrderAuditMapper orderAuditMapper,
                                         AddressHistoryMapper addressHistoryMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertProductInformation = insertProductInformation;
        this.insertAddressInformation = insertAddressInformation;
        this.insertApartmentInformation = insertApartmentInformation;
        this.addressHistory = addressHistory;
        this.orderAudit = orderAudit;
        this.levelsHistory = levelsHistory;
        this.levelHistoryMapper = levelHistoryMapper;
        this.orderAuditMapper = orderAuditMapper;
        this.addressHistoryMapper = addressHistoryMapper;
    }

    @Override
    public ProductInformationDto createNewProduct(ProductInformationDto productInformationDto) {
        log.info("Start to insertion of Product Information...");
        jdbcTemplate.update(insertProductInformation, productInformationDto.getDescription());
        return productInformationDto;
    }

    @Override
    public AddressDto createNewAddress(AddressDto addressDto) {
        log.info("Start to insertion of Address...");
        jdbcTemplate.update(insertAddressInformation, addressDto.getStreet(), addressDto.getHousenumber());
        return addressDto;
    }

    @Override
    public ApartmentDto createNewApartment(ApartmentDto apartmentDto) {
        log.info("Start to insertion of Apartment...");
        jdbcTemplate.update(insertApartmentInformation,
                apartmentDto.getAptQuantityofrooms(), apartmentDto.getAptFree(),
                apartmentDto.getAptSale(), apartmentDto.getAddressId(), apartmentDto.getProductInformationId());
        return apartmentDto;
    }

    @Override
    public List<CustomerLevelHistoryDto> customerLevelsHistory() {
        List<CustomerLevelHistory> overview = jdbcTemplate.query(levelsHistory, new BeanPropertyRowMapper<>(CustomerLevelHistory.class));
        return overview.stream()
                .map(levelHistoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderAuditDto> ordersOverview() {
        List<OrderAudit> overview = jdbcTemplate.query(orderAudit, new BeanPropertyRowMapper<>(OrderAudit.class));
        return overview.stream()
                .map(orderAuditMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerAddressHistoryDto> customerAddressHistory() {
        List<CustomerAddressHistory> overview = jdbcTemplate.query(addressHistory, new BeanPropertyRowMapper<>(CustomerAddressHistory.class));
        return overview.stream()
                .map(addressHistoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
