package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Leveloverview}
 */
@Value
public class LeveloverviewDto implements Serializable {
    @NotNull
    @Size(max = 25)
    String customerName;
    @NotNull
    @Size(max = 25)
    String customerLastname;
    @NotNull
    @Size(max = 10)
    String levelDescription;
    @NotNull
    @Size(max = 100)
    String reward;
}