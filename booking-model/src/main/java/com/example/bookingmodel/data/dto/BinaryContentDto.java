package com.example.bookingmodel.data.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.bookingmodel.data.entity.BinaryContent}
 */
@Value
public class BinaryContentDto implements Serializable {
    Long id;
    @NotNull
    @Size(max = 255)
    String fileName;
    @NotNull
    @Size(max = 10)
    String fileExtension;
    @NotNull
    byte[] content;
    @NotNull
    LocalDate uploadDate;
    LocalDate modificationDate;
    @Size(max = 255)
    String operationPerformedBy;
}