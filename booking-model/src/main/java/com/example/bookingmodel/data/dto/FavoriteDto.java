package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Favorite}
 */
@Value
public class FavoriteDto implements Serializable {
    Long id;
    @NotNull
    Long favQuantityofchoices;
}