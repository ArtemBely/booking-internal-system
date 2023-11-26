package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.CustomerAddressHistoryDto;
import com.example.bookingmodel.data.entity.CustomerAddressHistory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressHistoryMapper {

    private final ModelMapper modelMapper;

    public CustomerAddressHistory mapToEntity(CustomerAddressHistoryDto dto) {
        return modelMapper.map(dto, CustomerAddressHistory.class).toBuilder().build();
    }

    public CustomerAddressHistoryDto mapToDto(CustomerAddressHistory entity) {
        return modelMapper.map(entity, CustomerAddressHistoryDto.class).toBuilder().build();
    }
}