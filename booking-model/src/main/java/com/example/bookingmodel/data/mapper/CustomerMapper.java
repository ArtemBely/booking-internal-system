package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.CustomerDto;
import com.example.bookingmodel.data.entity.Customer;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomerMapper {

    private final ModelMapper modelMapper;

    public Customer mapToEntity(CustomerDto dto) {
        return modelMapper.map(dto, Customer.class).toBuilder().build();
    }

    public CustomerDto mapToDto(Customer entity) {
        return modelMapper.map(entity, CustomerDto.class).toBuilder().build();
    }
}
