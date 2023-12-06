package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Level}
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LevelDto implements Serializable {

    @JsonIgnore
    private Long id;

    @NotNull
    @Size(max = 10)
    String title;
}