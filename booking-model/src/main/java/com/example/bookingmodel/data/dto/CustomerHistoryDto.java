package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerHistoryDto {

    @JsonIgnore
    private Long id;

    @NotNull
    private Long customerId;

    private Long oldAddressId;

    private Long newAddressId;

    private Date changeDate;

    private String addressChangeLevel;

    private String addressPath;

}
