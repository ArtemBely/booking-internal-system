package com.example.bookingmodel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PERMISSION")
public class Permission {
    @Id
    @Column(name = "ID_PERMISSION", nullable = false)
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ROLE")
    private Role idRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ABILITY")
    private Ability idAbility;

}