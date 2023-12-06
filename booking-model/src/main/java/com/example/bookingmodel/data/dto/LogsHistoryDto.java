package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.LogsHistory}
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LogsHistoryDto implements Serializable {

    @JsonIgnore
    Long id;
    @NotNull
    @Size(max = 1000)
    String description;
}