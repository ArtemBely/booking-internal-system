package com.example.bookingmodel.data.entity;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LEVELOVERVIEW")
public class Leveloverview {

    @Id
    @Size(max = 25)
    @NotNull
    @Column(name = "\"CUSTOMER NAME\"", nullable = false, length = 25)
    private String customerName;

    @Size(max = 25)
    @NotNull
    @Column(name = "\"CUSTOMER LASTNAME\"", nullable = false, length = 25)
    private String customerLastname;

    @Size(max = 10)
    @NotNull
    @Column(name = "\"LEVEL DESCRIPTION\"", nullable = false, length = 10)
    private String levelDescription;

    @Size(max = 100)
    @NotNull
    @Column(name = "REWARD", nullable = false, length = 100)
    private String reward;

}