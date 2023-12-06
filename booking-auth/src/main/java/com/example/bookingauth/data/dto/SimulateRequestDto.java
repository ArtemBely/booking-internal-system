package com.example.bookingauth.data.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimulateRequestDto {

    @NonNull
    private String email;
}