package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Apartmentsoverview}
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentsoverviewDto implements Serializable {

    private int id;

    @NotNull
    @Size(max = 30)
    String street;

    int housenumber;

    @Size(max = 500)
    String description;

    int quantityofrooms;

    int aptfree;

    int aptsale;
}