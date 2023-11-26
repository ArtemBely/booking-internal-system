package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Apartment}
 */
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class ApartmentDto implements Serializable {

    @JsonIgnore
    Long aptId;
    @NotNull
    Short aptQuantityofrooms;
    @NotNull
    Long aptFree;
    @NotNull
    Long aptSale;
    Long customerId;
    @NotNull
    Long addressId;
    Long waitingListId;
    @NotNull
    Long productInformationId;
    Long contentId;
}