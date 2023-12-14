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
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Arrays;
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

    private final OrdersOverviewMapper ordersOverviewMapper;

    private final LevelMapper levelMapper;

    private final RewardMapper rewardMapper;

    private final OrderMapper orderMapper;

    private final JdbcTemplate jdbcTemplate;

    private String getAllUsers, getAllUserRoles, getAllOrders,
            getOrdersByUser, getOverview, getLevelsOverview, getWaitingListOverview,
            getUserStatus, getOrdersOverview, allLevels, getReward, calculateAllOrders;

    @Autowired
    public AdminUsersManipulationService(CustomerRepository customerRepository,
                                         CustomerMapper customerMapper,
                                         CustomerOverviewMapper customerOverviewMapper,
                                         WLOverviewMapper wlOverviewMapper,
                                         LevelOverviewMapper levelOverviewMapper,
                                         OrderMapper orderMapper,
                                         LevelMapper levelMapper,
                                         RewardMapper rewardMapper,
                                         OrdersOverviewMapper ordersOverviewMapper,
                                         JdbcTemplate jdbcTemplate,
                                         @Value("${sql.allUsers}") String findRolesByCustomerIdQuery,
                                         @Value("${sql.findRolesByCustomerId}") String getAllUserRoles,
                                         @Value("${sql.allOrders}") String getAllOrders,
                                         @Value("${sql.getOrdersByUser}") String getOrdersByUser,
                                         @Value("${sql.getOverview}") String getOverview,
                                         @Value("${sql.getLevelsOverview}") String getLevelsOverview,
                                         @Value("${sql.getOrdersOverview}") String getOrdersOverview,
                                         @Value("${sql.getWaitingListOverview}") String getWaitingListOverview,
                                         @Value("${sql.getUserStatus}") String getUserStatus,
                                         @Value("${sql.allLevels}") String allLevels,
                                         @Value("${sql.calculateAllOrders}") String calculateAllOrders,
                                         @Value("${sql.getReward}") String getReward
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
        this.allLevels = allLevels;
        this.calculateAllOrders = calculateAllOrders;
        this.getReward = getReward;
        this.getOrdersOverview = getOrdersOverview;
        this.getWaitingListOverview = getWaitingListOverview;
        this.getLevelsOverview = getLevelsOverview;
        this.levelOverviewMapper = levelOverviewMapper;
        this.ordersOverviewMapper = ordersOverviewMapper;
        this.wlOverviewMapper = wlOverviewMapper;
        this.levelMapper = levelMapper;
        this.rewardMapper = rewardMapper;
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

    @Override
    public List<CustomerHistoryDto> getUserHistory(int id) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter(Types.INTEGER),
                new SqlOutParameter("cur_out", OracleTypes.CURSOR, new CustomerAddressHistoryMapper()) // Выходной курсор
        );

        Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(java.sql.Connection connection) throws SQLException {
                CallableStatement callableStatement = connection.prepareCall("{CALL GetCustomerAddressHistory(?, ?)}");
                callableStatement.setInt(1, id);
                callableStatement.registerOutParameter(2, OracleTypes.CURSOR); // OracleTypes может потребовать импорта
                return callableStatement;
            }
        }, parameters);

        return (List<CustomerHistoryDto>) resultMap.get("cur_out");
    }

    @Override
    public List<OrdersoverviewDto> findOrdersOverview() {
        List<Ordersoverview> overview = jdbcTemplate.query(getOrdersOverview, new BeanPropertyRowMapper<>(Ordersoverview.class));
        return overview.stream()
                .map(ordersOverviewMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LevelDto> getAllLevels() {
        List<Level> overview = jdbcTemplate.query(allLevels, new BeanPropertyRowMapper<>(Level.class));
        return overview.stream()
                .map(levelMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RewardDto> getRewards() {
        List<Reward> overview = jdbcTemplate.query(getReward, new BeanPropertyRowMapper<>(Reward.class));
        return overview.stream()
                .map(rewardMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public int calculateOrders() {
        return jdbcTemplate.queryForObject(calculateAllOrders, Integer.class);
    }

    /**
     * Method delete customer entity globally, with child rows from another tables (by using SQL procedure)
     * @param id user ID to delete
     */
    public void deleteUser(int id) {
        try {
            jdbcTemplate.update(
                    connection -> {
                        CallableStatement cs = connection.prepareCall("{CALL DeleteCustomerCascade(?)}");
                        cs.setInt(1, id);
                        return cs;
                    }
            );
        } catch (Exception ex) {
            log.error("Error deleting user: {}", ex.getMessage());
            throw ex; // Rethrow the exception to let the controller handle it
        }
    }

    private static class CustomerAddressHistoryMapper implements RowMapper<CustomerHistoryDto> {
        @Override
        public CustomerHistoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            CustomerHistoryDto history = new CustomerHistoryDto();
            history.setId((long) rs.getInt("history_id"));
            history.setCustomerId((long) rs.getInt("customer_id"));
            history.setOldAddressId((long) rs.getInt("old_address_id"));
            history.setNewAddressId((long) rs.getInt("new_address_id"));
            history.setChangeDate(rs.getDate("change_date"));
            history.setAddressChangeLevel(String.valueOf(rs.getInt("address_change_level")));
            history.setAddressPath(rs.getString("address_path"));
            return history;
        }
    }
}
