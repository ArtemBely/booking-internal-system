package com.example.bookingmodel.data.dto;

import com.example.bookingmodel.data.entity.Role;
import com.example.bookingmodel.data.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    @JsonIgnore
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String phone;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private String email;

    private String password;


    private LocalDate dateofbirth;


    private Long levelId;


    private Long addressId;


    private Long contentId;

//    @NotNull
    private List<RoleDto> roles;

}
