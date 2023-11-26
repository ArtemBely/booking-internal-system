package com.example.bookingmodel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @NotNull
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "STREET", nullable = false, length = 30)
    private String street;

    @NotNull
    @Column(name = "HOUSENUMBER", nullable = false)
    private Long housenumber;

}