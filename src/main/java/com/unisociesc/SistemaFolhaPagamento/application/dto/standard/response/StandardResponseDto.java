package com.unisociesc.SistemaFolhaPagamento.application.dto.standard.response;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorResponseDto;

/**
 * Response DTO representing the payroll data of a standard collaborator.
 *
 * <p>A standard collaborator receives no bonuses or commissions, so the
 * {@code extras} field is always {@code 0.0} and the {@code finalSalary}
 * is equal to the {@code baseSalary}.</p>
 *
 * @param name               the full name of the collaborator
 * @param registrationNumber the unique registration number of the collaborator
 * @param baseSalary         the base salary configured for the company
 * @param extras             additional earnings beyond the base salary; always {@code 0.0} for standard collaborators
 * @param finalSalary        the total calculated salary; equals {@code baseSalary} for standard collaborators
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see CollaboratorResponseDto
 */
public record StandardResponseDto(

        String name,

        Integer registrationNumber,

        Double baseSalary,

        Double extras,

        Double finalSalary

) implements CollaboratorResponseDto {
}