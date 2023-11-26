package com.example.bookingmodel.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT_INFORMATION")
@AllArgsConstructor
@NoArgsConstructor
public class ProductInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 500)
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

}