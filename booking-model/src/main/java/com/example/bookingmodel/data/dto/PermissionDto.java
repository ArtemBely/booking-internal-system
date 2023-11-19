package com.example.bookingmodel.data.dto;

import com.example.bookingmodel.data.entity.Ability;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.Permission}
 */
@Value
public class PermissionDto implements Serializable {
    Short id;
    int idRole;
    int idAbility;
}