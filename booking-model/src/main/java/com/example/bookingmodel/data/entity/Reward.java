package com.example.bookingmodel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "REWARD")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @NotNull
    @Column(name = "LEVEL_ID", nullable = false)
    private Long levelId;

}