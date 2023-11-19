package com.example.bookingmodel.data.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Role")
@Builder(toBuilder = true)
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Setter
@Getter
@Component
@ToString
public class Role {

    @Id
    @Column(name = "ID_ROLE", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 50)
    @Column(name = "ROLENAME", length = 50, nullable = false)
    private String rolename;

    @Size(max = 50)
    @Column(name = "LDAP_ROLE", length = 50, nullable = false)
    private String ldapRole;

    @Size(max = 255)
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles")
    private List<Customer> customerEntities;

    @OneToMany(mappedBy = "idRole")
    private Set<Permission> permissions = new LinkedHashSet<>();

//    @OneToMany(mappedBy = "idRole")
//    private Set<UserRole> userRoles = new LinkedHashSet<>();

}
