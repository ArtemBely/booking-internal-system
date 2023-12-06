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
@Table(name = "BESTSELLERSOVERVIEW")
public class Bestsellersoverview {
    @Id
    @NotNull
    @Column(name = "ADDRESS_ID", nullable = false)
    private Long addressId;

    @NotNull
    @Column(name = "FAV_QUANTITYOFCHOICES", nullable = false)
    private Long favQuantityofchoices;

    @Size(max = 30)
    @NotNull
    @Column(name = "STREET", nullable = false, length = 30)
    private String street;

    @NotNull
    @Column(name = "HOUSENUMBER", nullable = false)
    private Long housenumber;

}