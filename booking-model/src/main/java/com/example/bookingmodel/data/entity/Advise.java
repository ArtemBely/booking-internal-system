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
@Table(name = "ADVISE")
public class Advise {

    @Id
    @NotNull
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 500)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 500)
    private String description;

    @Size(max = 500)
    @NotNull
    @Column(name = "RESTOFPURCHASE", nullable = false, length = 500)
    private String restofpurchase;

    @NotNull
    @Column(name = "LEVEL_ID", nullable = false)
    private Long levelId;

}