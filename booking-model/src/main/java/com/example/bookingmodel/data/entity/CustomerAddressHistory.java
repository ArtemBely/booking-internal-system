package com.example.bookingmodel.data.entity;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@Entity
@Immutable
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER_ADDRESS_HISTORY")
public class CustomerAddressHistory {
    @Id
    @Column(name = "HISTORY_ID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "OLD_ADDRESS_ID")
    private Long oldAddressId;

    @Column(name = "NEW_ADDRESS_ID")
    private Long newAddressId;

    @Column(name = "CHANGE_DATE")
    private Instant changeDate;

}