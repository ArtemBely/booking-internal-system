package com.example.bookingmodel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "CASH")
public class Cash {

    @Id
    @NotNull
    @Column(name = "ORD_ID", nullable = false)
    private Long ordId;

    @NotNull
    @Column(name = "CSH_CONVERSION", nullable = false)
    private Long cshConversion;

}