package com.example.bookingmodel.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateLevelRequestDto {

    @NonNull
    private Integer[] customerIds;

    @NonNull
    private int newLevelId;

}