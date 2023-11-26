package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.CustomerLevelHistory}
 */
@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerLevelHistoryDto implements Serializable {

    @JsonIgnore
    Long id;
    Long customerId;
    Long oldLevelId;
    Long newLevelId;
    Instant updateTimestamp;
}