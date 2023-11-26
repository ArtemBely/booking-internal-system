package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.CustomeroverviewDto;
import com.example.bookingmodel.data.entity.Customeroverview;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerOverviewMapper {

    private final ModelMapper modelMapper;

    public Customeroverview mapToEntity(CustomeroverviewDto dto) {
        return modelMapper.map(dto, Customeroverview.class).toBuilder().build();
    }

    public CustomeroverviewDto mapToDto(Customeroverview entity) {
        return modelMapper.map(entity, CustomeroverviewDto.class).toBuilder().build();
    }
}
