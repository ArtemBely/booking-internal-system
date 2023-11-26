package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.CustomerAddressHistory}
 */
@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerAddressHistoryDto implements Serializable {

    @JsonIgnore
    Long id;
    @NotNull
    Long customerId;
    Long oldAddressId;
    Long newAddressId;
    Instant changeDate;
}