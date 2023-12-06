package com.example.bookingmodel.data.entity;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
@Table(name = "ORDERSOVERVIEW")
public class Ordersoverview {
    @Id
    @NotNull
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 25)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 25)
    private String name;

    @Size(max = 25)
    @NotNull
    @Column(name = "SURNAME", nullable = false, length = 25)
    private String surname;

    @NotNull
    @Column(name = "ORD_DATE", nullable = false)
    private LocalDate ordDate;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

}