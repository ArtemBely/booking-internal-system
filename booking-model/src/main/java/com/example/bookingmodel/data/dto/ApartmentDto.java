package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Apartment}
 */
@Value
public class ApartmentDto implements Serializable {

    @NotNull
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