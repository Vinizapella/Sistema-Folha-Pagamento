package com.unisociesc.SistemaFolhaPagamento.application.dto.production.response;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorResponseDto;

/**
 * Response DTO representing the payroll data of a production-based collaborator.
 *
 * <p>A production collaborator receives the base salary plus a production bonus
 * calculated from the number of pieces produced and the value per piece:</p>
 *
 * <pre>
 *   extras = valuePerPiece * quantityProduced
 *   finalSalary = baseSalary + extras
 * </pre>
 *
 * @param name               the full name of the collaborator
 * @param registrationNumber the unique registration number of the collaborator
 * @param baseSalary         the base salary configured for the company
 * @param extras             the production bonus earned beyond the base salary
 * @param finalSalary        the total calculated salary including the production bonus
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see CollaboratorResponseDto
 */
public record ProductionResponseDto(

        String name,

        Integer registrationNumber,

        Double baseSalary,

        Double extras,

        Double finalSalary

) implements CollaboratorResponseDto {
}