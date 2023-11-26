package com.example.bookingmodel.data.entity;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@Entity
@Immutable
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER_LEVEL_HISTORY")
public class CustomerLevelHistory {
    @Id
    @Column(name = "HISTORY_ID", nullable = false)
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "OLD_LEVEL_ID")
    private Long oldLevelId;

    @Column(name = "NEW_LEVEL_ID")
    private Long newLevelId;

    @Column(name = "UPDATE_TIMESTAMP")
    private Instant updateTimestamp;

}