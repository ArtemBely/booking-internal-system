package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Favorite}
 *
 */

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto implements Serializable {

    @JsonIgnore
    private Long id;

    @NotNull
    private Long favQuantityofchoices;

    @NotNull
    private int apartment_id;
}