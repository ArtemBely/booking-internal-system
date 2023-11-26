package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.OrderAuditDto;
import com.example.bookingmodel.data.entity.OrderAudit;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderAuditMapper {

    private final ModelMapper modelMapper;

    public OrderAudit mapToEntity(OrderAuditDto dto) {
        return modelMapper.map(dto, OrderAudit.class).toBuilder().build();
    }

    public OrderAuditDto mapToDto(OrderAudit entity) {
        return modelMapper.map(entity, OrderAuditDto.class).toBuilder().build();
    }
}