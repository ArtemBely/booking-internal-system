package com.example.bookingmodel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "OTHER")
public class Other {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APT_ID", nullable = false)
    private Long id;

    @Size(max = 500)
    @Column(name = "OTH_COMMENT", length = 500)
    private String othComment;

    @NotNull
    @Column(name = "OTH_ID", nullable = false)
    private Long othId;

}