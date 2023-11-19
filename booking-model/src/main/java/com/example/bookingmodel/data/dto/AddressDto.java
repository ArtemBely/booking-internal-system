package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Address}
 */
@Value
public class AddressDto implements Serializable {
    @NotNull
    Long id;
    @NotNull
    @Size(max = 30)
    String street;
    @NotNull
    Long housenumber;
    @NotNull
    Long orderOrdId;
}