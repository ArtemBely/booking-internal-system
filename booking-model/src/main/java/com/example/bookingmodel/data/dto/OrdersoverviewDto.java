package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Ordersoverview}
 */
@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdersoverviewDto implements Serializable {

    @NotNull
    Long id;
    @NotNull
    @Size(max = 25)
    String name;
    @NotNull
    @Size(max = 25)
    String surname;
    @NotNull
    LocalDate ordDate;

    @NotNull
    String description;
}