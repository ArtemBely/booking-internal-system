package com.example.bookingmodel.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GlobalApartmentDto {


    @NonNull
    private String street;

    @NonNull
    private int houseNumber;

    @NonNull
    private String description;

    @NonNull
    private int quantityOfRooms;

    @NonNull
    private int aptFree;

    @NonNull
    private int aptSale;

}