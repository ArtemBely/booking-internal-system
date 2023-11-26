package com.example.bookingmodel.data.entity;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMEROVERVIEW")
public class Customeroverview {

    @Id
    @NotNull
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 25)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 25)
    private String name;

    @Size(max = 25)
    @NotNull
    @Column(name = "SURNAME", nullable = false, length = 25)
    private String surname;

    @Size(max = 30)
    @NotNull
    @Column(name = "STREET", nullable = false, length = 30)
    private String street;


    @Size(max = 50)
    @Column(name = "ROLENAME", length = 50)
    private String rolename;

}