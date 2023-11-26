package com.example.bookingmodel.data.entity;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Immutable
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_AUDIT")
public class OrderAudit {
    @Id
    @Column(name = "AUDIT_ID", nullable = false)
    private Long id;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "AUDIT_DATE")
    private LocalDate auditDate;

}