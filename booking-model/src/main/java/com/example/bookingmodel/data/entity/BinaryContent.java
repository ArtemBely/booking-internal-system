package com.example.bookingmodel.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "BINARY_CONTENT")
public class BinaryContent {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @Size(max = 10)
    @NotNull
    @Nationalized
    @Column(name = "FILE_EXTENSION", nullable = false, length = 10)
    private String fileExtension;

    @NotNull
    @Column(name = "CONTENT", nullable = false)
    private byte[] content;

    @NotNull
    @Column(name = "UPLOAD_DATE", nullable = false)
    private LocalDate uploadDate;

    @Column(name = "MODIFICATION_DATE")
    private LocalDate modificationDate;

    @Size(max = 255)
    @Nationalized
    @Column(name = "OPERATION_PERFORMED_BY")
    private String operationPerformedBy;

}