package com.example.bookingmodel.data.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdentRequestDto {

    @NonNull
    private int id;
}