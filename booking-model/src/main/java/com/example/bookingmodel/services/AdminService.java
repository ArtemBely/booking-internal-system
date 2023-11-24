package com.example.bookingmodel.services;

import com.example.bookingmodel.data.dto.CustomerDto;
import com.example.bookingmodel.data.entity.Customer;
import com.example.bookingmodel.data.entity.Role;
import com.example.bookingmodel.data.mapper.CustomerMapper;
import com.example.bookingmodel.interfaces.IAdminService;
import com.example.bookingmodel.interfaces.IUserService;
import com.example.bookingmodel.repositories.CustomerRepository;
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
public class AdminService implements IAdminService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final JdbcTemplate jdbcTemplate;

    private String getAllUsers;

    private String getAllUserRoles;

    @Autowired
    public AdminService(CustomerRepository customerRepository,
                       CustomerMapper customerMapper,
                       JdbcTemplate jdbcTemplate,
                       @Value("${sql.allUsers}") String findRolesByCustomerIdQuery,
                       @Value("${sql.findRolesByCustomerId}") String getAllUserRoles) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.getAllUsers = findRolesByCustomerIdQuery;
        this.getAllUserRoles = getAllUserRoles;
    }

    @Override
    public List<CustomerDto> findAllUsersByQuery() {
        String sd = getAllUsers;
        List<Customer> customers = jdbcTemplate.query(sd, new BeanPropertyRowMapper<>(Customer.class));
        setCustomersRoles(customers);
        return customers
                .stream()
                .map(customerMapper::mapToDto)
                .collect(Collectors.toList());
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
}
