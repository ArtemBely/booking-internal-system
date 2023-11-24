package com.example.bookingmodel.data.entity;

import lombok.Getter;
import lombok.Setter;
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
@Table(name = "WAITINGLISTOVERVIEW")
public class Waitinglistoverview {

    @Id
    @NotNull
    @Column(name = "\"COUNT OF PERSON\"", nullable = false)
    private Long countOfPerson;

    @Size(max = 25)
    @NotNull
    @Column(name = "\"CUSTOMER NAME\"", nullable = false, length = 25)
    private String customerName;

    @NotNull
    @Column(name = "\"HAS DISCOUNT\"", nullable = false)
    private Long hasDiscount;

    @Size(max = 30)
    @NotNull
    @Column(name = "STREET", nullable = false, length = 30)
    private String street;

}