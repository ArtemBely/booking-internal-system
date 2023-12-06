package com.example.bookingmodel.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LOGS_HISTORY")
public class LogsHistory {
    @Id
    @NotNull
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 1000)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 1000)
    private String description;

}