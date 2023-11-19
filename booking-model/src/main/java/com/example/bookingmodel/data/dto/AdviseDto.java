package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Advise}
 */
@Value
public class AdviseDto implements Serializable {
    @NotNull
    Long id;
    @NotNull
    @Size(max = 500)
    String description;
    @NotNull
    @Size(max = 500)
    String restofpurchase;
    @NotNull
    Long levelId;
}