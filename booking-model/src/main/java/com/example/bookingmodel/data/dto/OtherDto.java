package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Other}
 */
@Value
public class OtherDto implements Serializable {
    Long id;
    @Size(max = 500)
    String othComment;
    @NotNull
    Long othId;
}