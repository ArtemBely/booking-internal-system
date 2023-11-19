package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.RoleDto;
import com.example.bookingmodel.data.entity.Role;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    public Role mapToEntity(RoleDto dto) {
        return modelMapper.map(dto, Role.class).toBuilder().build();
    }

    public RoleDto mapToDto(Role entity) {
        return modelMapper.map(entity, RoleDto.class).toBuilder().build();
    }
}
