package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Order}
 */
@Value
public class OrderDto implements Serializable {
    @NotNull
    Long ordId;
    @NotNull
    LocalDate ordDate;
    @NotNull
    Long ordAmount;
    @NotNull
    Long productInformationId;
    @NotNull
    Long customerId;
}