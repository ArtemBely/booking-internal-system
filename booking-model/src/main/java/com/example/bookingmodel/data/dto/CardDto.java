package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Card}
 */
@Value
public class CardDto implements Serializable {
    @NotNull
    Long ordId;
    @NotNull
    Long crdCardnumber;
}