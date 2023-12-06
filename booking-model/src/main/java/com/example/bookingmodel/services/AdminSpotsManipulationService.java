package com.example.bookingmodel.services;

import com.example.bookingmodel.data.dto.*;
import com.example.bookingmodel.data.entity.*;
import com.example.bookingmodel.data.mapper.*;
import com.example.bookingmodel.interfaces.IAdminSpotsManipulationService;
import com.example.bookingmodel.utilities.DefaultConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminSpotsManipulationService implements IAdminSpotsManipulationService {


    private final JdbcTemplate jdbcTemplate;

    private final LevelHistoryMapper levelHistoryMapper;

    private final OrderAuditMapper orderAuditMapper;

    private final BestsellersMapper bestsellersMapper;

    private final FavoritesMapper favoritesMapper;

    private final AddressHistoryMapper addressHistoryMapper;

    private final LogsHistoryMapper logsHistoryMapper;

    private String insertProductInformation, insertAddressInformation, insertApartmentInformation,
            orderAudit, addressHistory, levelsHistory, callFavorites, getFavorites, getBestsellersOverview,
            getLogs, calculateProfitByUser;

    @Autowired
    public AdminSpotsManipulationService(JdbcTemplate jdbcTemplate,
                                         @Value("${sql.insertProductInformation}") String insertProductInformation,
                                         @Value("${sql.insertAddress}") String insertAddressInformation,
                                         @Value("${sql.insertApartment}") String insertApartmentInformation,
                                         @Value("${sql.addressHistory}") String addressHistory,
                                         @Value("${sql.orderAudit}") String orderAudit,
                                         @Value("${sql.levelsHistory}") String levelsHistory,
                                         @Value("${sql.getFavorites}") String getFavorites,
                                         @Value("${sql.callFavorites}") String callFavorites,
                                         @Value("${sql.getBestsellersOverview}") String getBestsellersOverview,
                                         @Value("${sql.getLogs}") String getLogs,
                                         @Value("${sql.calculateProfitByUser}") String calculateProfitByUser,
                                         LevelHistoryMapper levelHistoryMapper,
                                         BestsellersMapper bestsellersMapper,
                                         FavoritesMapper favoritesMapper,
                                         OrderAuditMapper orderAuditMapper,
                                         AddressHistoryMapper addressHistoryMapper,
                                         LogsHistoryMapper logsHistoryMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertProductInformation = insertProductInformation;
        this.insertAddressInformation = insertAddressInformation;
        this.insertApartmentInformation = insertApartmentInformation;
        this.addressHistory = addressHistory;
        this.orderAudit = orderAudit;
        this.levelsHistory = levelsHistory;
        this.getBestsellersOverview = getBestsellersOverview;
        this.getFavorites = getFavorites;
        this.calculateProfitByUser = calculateProfitByUser;
        this.callFavorites = callFavorites;
        this.levelHistoryMapper = levelHistoryMapper;
        this.orderAuditMapper = orderAuditMapper;
        this.bestsellersMapper = bestsellersMapper;
        this.addressHistoryMapper = addressHistoryMapper;
        this.favoritesMapper = favoritesMapper;
        this.logsHistoryMapper = logsHistoryMapper;
        this.getLogs = getLogs;
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

    public String createAddressApartmentProduct(String street, int houseNumber, String description, int quantityOfRooms, int aptFree, int aptSale) {
        try {
            jdbcTemplate.update(
                    connection -> {
                        CallableStatement cs = connection.prepareCall(
                                "{CALL create_address_apartment_product(?, ?, ?, ?, ?, ?)}"
                        );
                        cs.setString(1, street);
                        cs.setInt(2, houseNumber);
                        cs.setString(3, description);
                        cs.setInt(4, quantityOfRooms);
                        cs.setInt(5, aptFree);
                        cs.setDouble(6, aptSale);
                        return cs;
                    }
            );
        } catch (Exception ex) {
            log.error("Error: {}", ex);
            return ex.getMessage();
        }
        return DefaultConstants.SUCCESS_MSG;
    }

    public List<FavoriteDto> updateFavoritesBasedOnHistory() {
        try {
            jdbcTemplate.execute("{CALL update_favorite_for_frequent_apartments}");
        } catch (DataAccessException ex) {
            log.error("Error occurred during update_favorites_based_on_history call", ex);
        } finally {
            List<Favorite> favorites = jdbcTemplate.query(getFavorites, new BeanPropertyRowMapper<>(Favorite.class));
            return favorites.stream()
                    .map(favoritesMapper::mapToDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<BestsellersoverviewDto> getBestsellers() {
        List<Bestsellersoverview> overview = jdbcTemplate.query(getBestsellersOverview, new BeanPropertyRowMapper<>(Bestsellersoverview.class));
        return overview.stream()
                .map(bestsellersMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LogsHistoryDto> retrieveLogs() {
        List<LogsHistory> overview = jdbcTemplate.query(getLogs, new BeanPropertyRowMapper<>(LogsHistory.class));
        return overview.stream()
                .map(logsHistoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal calculateTotalOrdersAmount(int customerId, String startDate, String endDate) {
        return jdbcTemplate.queryForObject(calculateProfitByUser, BigDecimal.class, customerId, startDate, endDate);
    }

}
