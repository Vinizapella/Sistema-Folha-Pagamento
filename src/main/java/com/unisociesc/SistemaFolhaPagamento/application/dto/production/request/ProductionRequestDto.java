package com.unisociesc.SistemaFolhaPagamento.application.dto.production.request;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductionRequestDto(

        @NotNull(message = "Registration number is required")
        Integer registrationNumber,

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotNull(message = "Value per piece is required")
        @Positive(message = "Value per piece must be greater than zero")
        Double valuePerPiece,

        @NotNull(message = "Quantity of pieces is required")
        @Positive(message = "Quantity of pieces must be at least one")
        Integer quantityProduced

) implements CollaboratorRequestDto {
}
