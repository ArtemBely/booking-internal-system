package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Ability}
 */
@Value
public class AbilityDto implements Serializable {
    Short id;
    @Size(max = 50)
    String signature;
    Set<PermissionDto> permissions;
}