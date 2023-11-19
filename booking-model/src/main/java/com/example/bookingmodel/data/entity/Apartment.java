package com.example.bookingmodel.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "APARTMENT")
public class Apartment {

    @Id
    @NotNull
    @Column(name = "APT_ID", nullable = false)
    private Long aptId;

    @NotNull
    @Column(name = "APT_QUANTITYOFROOMS", nullable = false)
    private Short aptQuantityofrooms;

    @NotNull
    @Column(name = "APT_FREE", nullable = false)
    private Long aptFree;

    @NotNull
    @Column(name = "APT_SALE", nullable = false)
    private Long aptSale;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @NotNull
    @Column(name = "ADDRESS_ID", nullable = false)
    private Long addressId;

    @Column(name = "WAITING_LIST_ID")
    private Long waitingListId;

    @NotNull
    @Column(name = "PRODUCT_INFORMATION_ID", nullable = false)
    private Long productInformationId;

}