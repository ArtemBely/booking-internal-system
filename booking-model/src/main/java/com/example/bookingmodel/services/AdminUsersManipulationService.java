package com.example.bookingmodel.services;

import com.example.bookingmodel.data.dto.*;
import com.example.bookingmodel.data.entity.*;
import com.example.bookingmodel.data.mapper.*;
import com.example.bookingmodel.interfaces.IAdminUsersManipulationService;
import com.example.bookingmodel.repositories.CustomerRepository;
import com.example.bookingmodel.utilities.AuthUtils;
import com.example.bookingmodel.utilities.DefaultConstants;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AdminUsersManipulationService implements IAdminUsersManipulationService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final CustomerOverviewMapper customerOverviewMapper;

    private final WLOverviewMapper wlOverviewMapper;

    private final LevelOverviewMapper levelOverviewMapper;

    private final OrderMapper orderMapper;

    private final JdbcTemplate jdbcTemplate;

    private String getAllUsers, getAllUserRoles, getAllOrders,
            getOrdersByUser, getOverview, getLevelsOverview, getWaitingListOverview,
            getUserStatus;

    @Autowired
    public AdminUsersManipulationService(CustomerRepository customerRepository,
                                         CustomerMapper customerMapper,
                                         CustomerOverviewMapper customerOverviewMapper,
                                         WLOverviewMapper wlOverviewMapper,
                                         LevelOverviewMapper levelOverviewMapper,
                                         OrderMapper orderMapper,
                                         JdbcTemplate jdbcTemplate,
                                         @Value("${sql.allUsers}") String findRolesByCustomerIdQuery,
                                         @Value("${sql.findRolesByCustomerId}") String getAllUserRoles,
                                         @Value("${sql.allOrders}") String getAllOrders,
                                         @Value("${sql.getOrdersByUser}") String getOrdersByUser,
                                         @Value("${sql.getOverview}") String getOverview,
                                         @Value("${sql.getLevelsOverview}") String getLevelsOverview,
                                         @Value("${sql.getWaitingListOverview}") String getWaitingListOverview,
                                         @Value("${sql.getUserStatus}") String getUserStatus
    ) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerOverviewMapper = customerOverviewMapper;
        this.orderMapper = orderMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.getAllUsers = findRolesByCustomerIdQuery;
        this.getAllUserRoles = getAllUserRoles;
        this.getAllOrders = getAllOrders;
        this.getOrdersByUser = getOrdersByUser;
        this.getOverview = getOverview;
        this.getWaitingListOverview = getWaitingListOverview;
        this.getLevelsOverview = getLevelsOverview;
        this.levelOverviewMapper = levelOverviewMapper;
        this.wlOverviewMapper = wlOverviewMapper;
        this.getUserStatus = getUserStatus;
    }

    @Override
    public List<CustomerDto> findAllUsersByQuery() {
        List<Customer> customers = jdbcTemplate.query(getAllUsers, new BeanPropertyRowMapper<>(Customer.class));
        setCustomersRoles(customers);
        return customers
                .stream()
                .map(customerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> orders = jdbcTemplate.query(getAllOrders, new BeanPropertyRowMapper<>(Order.class));
        return orders.stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAllOrdersByUser(int id) {
        List<Order> orders = jdbcTemplate.query(getOrdersByUser, new BeanPropertyRowMapper<>(Order.class), id);
        return orders.stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomeroverviewDto> getOverview() {
        List<Customeroverview> overview = jdbcTemplate.query(getOverview, new BeanPropertyRowMapper<>(Customeroverview.class));
        return overview.stream()
                .map(customerOverviewMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeveloverviewDto> getLevels() {
        List<Leveloverview> overview = jdbcTemplate.query(getLevelsOverview, new BeanPropertyRowMapper<>(Leveloverview.class));
        return overview.stream()
                .map(levelOverviewMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WaitinglistoverviewDto> getWaitngLists() {
        List<Waitinglistoverview> overview = jdbcTemplate.query(getWaitingListOverview, new BeanPropertyRowMapper<>(Waitinglistoverview.class));
        return overview.stream()
                .map(wlOverviewMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public String getUserStatus() {
        return jdbcTemplate.queryForObject(getUserStatus, new Object[]{AuthUtils.getActualId()}, String.class);
    }

    private void setCustomersRoles(List<Customer> customers) {
        customers.forEach(customer -> {
            List<Role> roles = findRolesByCustomerId(customer.getId());
            customer.setRoles(roles);
        });
    }

    private List<Role> findRolesByCustomerId(int customerId) {
        return jdbcTemplate.query(getAllUserRoles, new BeanPropertyRowMapper<>(Role.class), customerId);
    }

    public String updateCustomersLevel(Integer[] customerIds, int newLevelId) {
        Connection connection = null;
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor("NUMBER_TABLE", connection.unwrap(OracleConnection.class));
            ARRAY arrayToPass = new ARRAY(descriptor, connection.unwrap(OracleConnection.class), customerIds);
            try (CallableStatement callableStatement = connection.prepareCall("{call update_customers_level(?, ?)}")) {
                callableStatement.setArray(1, arrayToPass);
                callableStatement.setInt(2, newLevelId);
                callableStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return DefaultConstants.SUCCESS_MSG;
        }
    }

    public String deleteApartments(Integer[] apartmentIds) {
        Connection connection = null;
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor("NUMBER_TABLE", connection.unwrap(OracleConnection.class));
            ARRAY oracleArray = new ARRAY(desc, connection.unwrap(OracleConnection.class), apartmentIds);
            try (CallableStatement callableStatement = connection.prepareCall("{call delete_apartments(?)}")) {
                callableStatement.setArray(1, oracleArray);
                callableStatement.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            return DefaultConstants.SUCCESS_MSG;
        }
    }
}
