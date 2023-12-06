package com.example.bookingmodel.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FAVORITE")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "FAV_QUANTITYOFCHOICES", nullable = false)
    private Long favQuantityofchoices;

    @NotNull
    @Column(name = "APARTMENT_ID", nullable = false)
    private Long apartment_id;

}