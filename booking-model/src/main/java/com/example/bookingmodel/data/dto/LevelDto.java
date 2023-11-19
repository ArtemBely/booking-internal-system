package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Level}
 */
@Value
public class LevelDto implements Serializable {
    @NotNull
    @Size(max = 10)
    String title;
}