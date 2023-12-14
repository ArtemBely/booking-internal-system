package com.example.bookingmodel.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {

    @JsonIgnore
    private int id;

    @NotEmpty
    @NonNull
    private String rolename;

    @NotEmpty
    @NonNull
    private String ldapRole;

    @NotEmpty
    @NonNull
    private String description;

    private List<CustomerDto> customerDTOList;
}
