package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.OrderDto;
import com.example.bookingmodel.data.entity.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderMapper {

    private final ModelMapper modelMapper;

    public Order mapToEntity(OrderDto dto) {
        return modelMapper.map(dto, Order.class).toBuilder().build();
    }

    public OrderDto mapToDto(Order entity) {
        return modelMapper.map(entity, OrderDto.class).toBuilder().build();
    }
}
