package com.example.bookingmodel.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "REWARD")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NotNull
    @Column(name = "LEVEL_ID", nullable = false)
    private Long levelId;

}