package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Order}
 */
@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto implements Serializable {

    @JsonIgnore
    Long ordId;
    @NotNull
    LocalDate ordDate;
    @NotNull
    Long ordAmount;
    @NotNull
    Long productInformationId;
    @NotNull
    Long customerId;
}