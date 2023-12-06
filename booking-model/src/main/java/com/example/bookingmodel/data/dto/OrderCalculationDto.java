package com.example.bookingmodel.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NotNull
public class OrderCalculationDto {

    @NotNull
    private int customerId;

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;
}
