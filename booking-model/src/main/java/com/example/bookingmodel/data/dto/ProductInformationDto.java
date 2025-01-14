package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.ProductInformation}
 */
@Value
public class ProductInformationDto implements Serializable {

    @JsonIgnore
    Long id;
    @Size(max = 500)
    String description;
}