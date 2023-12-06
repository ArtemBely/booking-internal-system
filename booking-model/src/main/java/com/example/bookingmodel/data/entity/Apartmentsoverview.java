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
@Table(name = "APARTMENTSOVERVIEW")
public class Apartmentsoverview {

    @Id
    @Column(name = "ID")
    private byte[] id;

    @Size(max = 30)
    @NotNull
    @Column(name = "STREET", nullable = false, length = 30)
    private String street;

    @NotNull
    @Column(name = "HOUSENUMBER", nullable = false)
    private int housenumber;

    @Size(max = 500)
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @NotNull
    @Column(name = "QUANTITYOFROOMS", nullable = false)
    private int quantityofrooms;

    @NotNull
    @Column(name = "APTFREE", nullable = false)
    private int aptfree;

    @NotNull
    @Column(name = "APTSALE", nullable = false)
    private int aptsale;

}