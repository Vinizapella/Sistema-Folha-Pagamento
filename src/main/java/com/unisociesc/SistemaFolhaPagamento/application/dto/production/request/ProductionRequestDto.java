package com.unisociesc.SistemaFolhaPagamento.application.dto.production.request;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Request DTO for creating or updating a production-based collaborator.
 *
 * <p>A production collaborator receives the base salary plus a bonus calculated
 * from the number of pieces produced and the value assigned per piece:</p>
 *
 * <pre>
 *   extras = valuePerPiece * quantityProduced
 *   finalSalary = baseSalary + extras
 * </pre>
 *
 * @param registrationNumber the unique registration number of the collaborator; must not be {@code null}
 * @param name               the full name of the collaborator; must not be blank
 * @param valuePerPiece      the monetary value assigned to each piece produced; must be greater than zero
 * @param quantityProduced   the total number of pieces produced in the period; must be at least one
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see CollaboratorRequestDto
 */
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