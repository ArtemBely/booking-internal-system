package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Customeroverview}
 */
@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomeroverviewDto implements Serializable {

    @JsonIgnore
    Long id;
    @NotNull
    @Size(max = 25)
    String name;
    @NotNull
    @Size(max = 25)
    String surname;
    @NotNull
    @Size(max = 30)
    String street;
    @Size(max = 50)
    String rolename;
}