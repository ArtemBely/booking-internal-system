package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.OrderAuditDto;
import com.example.bookingmodel.data.dto.OrdersoverviewDto;
import com.example.bookingmodel.data.entity.OrderAudit;
import com.example.bookingmodel.data.entity.Ordersoverview;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrdersOverviewMapper {

    private final ModelMapper modelMapper;

    public Ordersoverview mapToEntity(OrdersoverviewDto dto) {
        return modelMapper.map(dto, Ordersoverview.class).toBuilder().build();
    }

    public OrdersoverviewDto mapToDto(Ordersoverview entity) {
        return modelMapper.map(entity, OrdersoverviewDto.class).toBuilder().build();
    }
}
