package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Reward}
 */
@Value
public class RewardDto implements Serializable {
    Long id;
    @NotNull
    @Size(max = 100)
    String title;
    @NotNull
    Long levelId;
}