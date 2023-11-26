package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Leveloverview}
 */
@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeveloverviewDto implements Serializable {
    @NotNull
    @Size(max = 25)
    String customerName;
    @NotNull
    @Size(max = 25)
    String customerLastname;
    @NotNull
    @Size(max = 10)
    String levelDescription;
    @NotNull
    @Size(max = 100)
    String reward;
}