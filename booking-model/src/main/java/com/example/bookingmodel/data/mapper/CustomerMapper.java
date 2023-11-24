package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.CustomerDto;
import com.example.bookingmodel.data.dto.RoleDto;
import com.example.bookingmodel.data.entity.Customer;
import com.example.bookingmodel.data.entity.Role;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerMapper {

    private final ModelMapper modelMapper;

    public Customer mapToEntity(CustomerDto dto) {
        return modelMapper.map(dto, Customer.class).toBuilder().build();
    }

    public CustomerDto mapToDto(Customer entity) {
        return modelMapper.map(entity, CustomerDto.class).toBuilder().build();
    }
}
