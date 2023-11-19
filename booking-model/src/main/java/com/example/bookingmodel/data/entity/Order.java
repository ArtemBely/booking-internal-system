package com.example.bookingmodel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "\"ORDER\"")
public class Order {

    @Id
    @NotNull
    @Column(name = "ORD_ID", nullable = false)
    private Long ordId;

    @NotNull
    @Column(name = "ORD_DATE", nullable = false)
    private LocalDate ordDate;

    @NotNull
    @Column(name = "ORD_AMOUNT", nullable = false)
    private Long ordAmount;

    @NotNull
    @Column(name = "PRODUCT_INFORMATION_ID", nullable = false)
    private Long productInformationId;

    @NotNull
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

}