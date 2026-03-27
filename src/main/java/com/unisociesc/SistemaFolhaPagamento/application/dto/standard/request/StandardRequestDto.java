package com.unisociesc.SistemaFolhaPagamento.application.dto.standard.request;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request DTO for creating or updating a standard collaborator.
 *
 * <p>A standard collaborator receives only the base salary, with no bonuses
 * or commissions. This DTO requires only the common collaborator fields.</p>
 *
 * @param registrationNumber the unique registration number of the collaborator; must not be {@code null}
 * @param name               the full name of the collaborator; must not be blank
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see CollaboratorRequestDto
 */
public record StandardRequestDto(

        @NotNull(message = "Registration number is required")
        Integer registrationNumber,

        @NotBlank(message = "Name cannot be blank")
        String name

) implements CollaboratorRequestDto {
}