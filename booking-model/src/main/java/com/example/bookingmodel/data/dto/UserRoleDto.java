package com.example.bookingmodel.data.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.UserRole}
 */
@Value
public class UserRoleDto implements Serializable {

    int id;

    int id_user;

    int id_role;
}