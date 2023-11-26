package com.example.bookingmodel.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteApartmentsDto {

    @NonNull
    private Integer[] aptsId;

}
