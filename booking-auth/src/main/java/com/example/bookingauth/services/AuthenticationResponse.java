package com.example.bookingauth.services;

import com.example.bookingmodel.data.dto.RoleDto;
import com.example.bookingmodel.data.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private int id;

    private String token;

    private String name;

    private String surname;

    private String email;

    private String phone;

    private Date dateOfBirth;

    private Long contentId;

    private Long levelId;

    private List<RoleDto> roles;
}
