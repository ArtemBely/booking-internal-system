package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Waitinglistoverview}
 */
@Value
public class WaitinglistoverviewDto implements Serializable {
    @NotNull
    Long countOfPerson;
    @NotNull
    @Size(max = 25)
    String customerName;
    @NotNull
    Long hasDiscount;
    @NotNull
    @Size(max = 30)
    String street;
}