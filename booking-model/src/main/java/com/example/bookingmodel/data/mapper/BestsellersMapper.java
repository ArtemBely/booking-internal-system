package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.ApartmentsoverviewDto;
import com.example.bookingmodel.data.dto.BestsellersoverviewDto;
import com.example.bookingmodel.data.entity.Apartmentsoverview;
import com.example.bookingmodel.data.entity.Bestsellersoverview;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BestsellersMapper {

    private final ModelMapper modelMapper;

    public Bestsellersoverview mapToEntity(BestsellersoverviewDto dto) {
        return modelMapper.map(dto, Bestsellersoverview.class).toBuilder().build();
    }

    public BestsellersoverviewDto mapToDto(Bestsellersoverview entity) {
        return modelMapper.map(entity, BestsellersoverviewDto.class).toBuilder().build();
    }
}
