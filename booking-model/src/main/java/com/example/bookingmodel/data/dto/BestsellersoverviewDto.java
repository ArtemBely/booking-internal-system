package com.example.bookingmodel.data.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Bestsellersoverview}
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BestsellersoverviewDto implements Serializable {

    @NotNull
    Long addressId;
    @NotNull
    Long favQuantityofchoices;
    @NotNull
    @Size(max = 30)
    String street;
    @NotNull
    Long housenumber;
}