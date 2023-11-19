package com.example.bookingmodel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ABILITY")
public class Ability {
    @Id
    @Column(name = "ID_ABILITY", nullable = false)
    private Short id;

    @Size(max = 50)
    @Column(name = "SIGNATURE", length = 50)
    private String signature;

    @OneToMany(mappedBy = "idAbility")
    private Set<Permission> permissions = new LinkedHashSet<>();

}