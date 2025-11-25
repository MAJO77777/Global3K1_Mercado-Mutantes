package com.example.parcial.dto;

import com.example.parcial.validator.ValidDnaSequence;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request para verificar si un ADN es mutante")
public class DnaRequest {

    @Schema(
            description = "Secuencia de ADN representada como matriz NxN",
            example = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "DNA no puede ser null")
    @NotEmpty(message = "DNA no puede estar vacío")
    @ValidDnaSequence
    private String[] dna;
}
