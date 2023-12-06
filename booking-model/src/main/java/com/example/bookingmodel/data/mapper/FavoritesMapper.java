package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.CustomerDto;
import com.example.bookingmodel.data.dto.FavoriteDto;
import com.example.bookingmodel.data.entity.Customer;
import com.example.bookingmodel.data.entity.Favorite;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FavoritesMapper {

    private final ModelMapper modelMapper;

    public Favorite mapToEntity(FavoriteDto dto) {
        return modelMapper.map(dto, Favorite.class).toBuilder().build();
    }

    public FavoriteDto mapToDto(Favorite entity) {
        return modelMapper.map(entity, FavoriteDto.class).toBuilder().build();
    }
}
