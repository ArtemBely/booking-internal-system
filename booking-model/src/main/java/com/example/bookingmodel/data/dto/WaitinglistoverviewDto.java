package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Waitinglistoverview}
 */
@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WaitinglistoverviewDto implements Serializable {
    @NotNull
    Long countOfPerson;
    @NotNull
    @Size(max = 25)
    String customerName;
    @NotNull
    Long hasDiscount;
    @NotNull
    @Size(max = 30)
    String street;
}