package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Customeroverview}
 */
@Value
public class CustomeroverviewDto implements Serializable {
    @NotNull
    Long id;
    @NotNull
    @Size(max = 25)
    String name;
    @NotNull
    @Size(max = 25)
    String surname;
    @NotNull
    @Size(max = 30)
    String street;
    Short idRole;
    @Size(max = 50)
    String rolename;
}