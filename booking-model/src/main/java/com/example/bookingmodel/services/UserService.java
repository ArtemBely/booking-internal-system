package com.example.bookingmodel.services;

import com.example.bookingmodel.data.dto.ApartmentsoverviewDto;
import com.example.bookingmodel.data.dto.CustomerDto;
import com.example.bookingmodel.data.entity.Apartmentsoverview;
import com.example.bookingmodel.data.entity.Customer;
import com.example.bookingmodel.data.entity.Role;
import com.example.bookingmodel.data.mapper.ApartmentsOverviewMapper;
import com.example.bookingmodel.data.mapper.CustomerMapper;
import com.example.bookingmodel.interfaces.IUserService;
import com.example.bookingmodel.repositories.CustomerRepository;
import com.example.bookingmodel.utilities.AuthUtils;
import com.example.bookingmodel.utilities.DefaultConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements IUserService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final ApartmentsOverviewMapper apartmentsOverviewMapper;

    private final JdbcTemplate jdbcTemplate;

    private String getNextLevel, getBirthdayData, getApartmentsOverview;

    @Autowired
    public UserService(CustomerRepository customerRepository,
                       CustomerMapper customerMapper,
                       ApartmentsOverviewMapper apartmentsOverviewMapper,
                       JdbcTemplate jdbcTemplate,
                       @Value("${sql.getNextLevel}") String getNextLevel,
                       @Value("${sql.getBirthdayData}") String getBirthdayData,
                       @Value("${sql.getApartmentsOverview}") String getApartmentsOverview) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.apartmentsOverviewMapper = apartmentsOverviewMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.getNextLevel = getNextLevel;
        this.getBirthdayData = getBirthdayData;
        this.getApartmentsOverview = getApartmentsOverview;
    }


    @Override
    public String getNextLevel() {
        return jdbcTemplate.queryForObject(getNextLevel, new Object[]{AuthUtils.getActualLevelId()}, String.class);
    }

    @Override
    public String getBirthdayData() {
        Object[] params = new Object[]{AuthUtils.getActualBirthday(), DefaultConstants.DATE_FORMAT};
        return jdbcTemplate.queryForObject(getBirthdayData, params, String.class);
    }

    @Override
    public List<ApartmentsoverviewDto> getGlobalApartment() {
        List<Apartmentsoverview> overview = jdbcTemplate.query(getApartmentsOverview, new BeanPropertyRowMapper<>(Apartmentsoverview.class));
        return overview.stream()
                .map(apartmentsOverviewMapper::mapToDto)
                .collect(Collectors.toList());
    }

}