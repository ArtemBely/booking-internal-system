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
@Table(name = "CARD")
public class Card {

    @Id
    @NotNull
    @Column(name = "ORD_ID", nullable = false)
    private Long ordId;

    @NotNull
    @Column(name = "CRD_CARDNUMBER", nullable = false)
    private Long crdCardnumber;

}