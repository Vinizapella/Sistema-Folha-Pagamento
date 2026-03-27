package com.unisociesc.SistemaFolhaPagamento.application.dto.commissioned.request;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorRequestDto;
import jakarta.validation.constraints.*;

/**
 * Request DTO for creating or updating a commissioned collaborator.
 *
 * <p>A commissioned collaborator receives the base salary plus a commission
 * calculated from their total sales and commission percentage:</p>
 *
 * <pre>
 *   extras = totalSales * percentageCommission / 100
 *   finalSalary = baseSalary + extras
 * </pre>
 *
 * @param registrationNumber   the unique registration number of the collaborator; must not be {@code null}
 * @param name                 the full name of the collaborator; must not be blank
 * @param totalSales           the total sales amount for the period; must be a positive value
 * @param percentageCommission the commission percentage applied over total sales;
 *                             must be between {@code 0.1} and {@code 100.0}
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see CollaboratorRequestDto
 */
public record CommissionedRequestDto(

        @NotNull(message = "Registration number is required")
        Integer registrationNumber,

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotNull(message = "Total sales must be provided")
        @Positive(message = "Total sales must be a positive value")
        Double totalSales,

        @NotNull(message = "Commission percentage is required")
        @DecimalMin(value = "0.1", message = "Commission cannot be negative")
        @DecimalMax(value = "100.0", message = "Commission cannot exceed 100%")
        Double percentageCommission

) implements CollaboratorRequestDto {
}