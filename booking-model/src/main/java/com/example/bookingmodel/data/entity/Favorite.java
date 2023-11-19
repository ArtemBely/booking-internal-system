package com.example.bookingmodel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "FAVORITE")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APT_ID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "FAV_QUANTITYOFCHOICES", nullable = false)
    private Long favQuantityofchoices;

}