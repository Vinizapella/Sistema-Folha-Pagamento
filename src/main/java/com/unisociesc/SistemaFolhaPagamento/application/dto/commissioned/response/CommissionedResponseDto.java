package com.unisociesc.SistemaFolhaPagamento.application.dto.commissioned.response;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorResponseDto;

/**
 * Response DTO representing the payroll data of a commissioned collaborator.
 *
 * <p>A commissioned collaborator receives the base salary plus a commission
 * calculated from their total sales and commission percentage:</p>
 *
 * <pre>
 *   extras = totalSales * percentageCommission / 100
 *   finalSalary = baseSalary + extras
 * </pre>
 *
 * @param name               the full name of the collaborator
 * @param registrationNumber the unique registration number of the collaborator
 * @param baseSalary         the base salary configured for the company
 * @param extras             the commission earned beyond the base salary
 * @param finalSalary        the total calculated salary including the commission
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see CollaboratorResponseDto
 */
public record CommissionedResponseDto(

        String name,

        Integer registrationNumber,

        Double baseSalary,

        Double extras,

        Double finalSalary

) implements CollaboratorResponseDto {
}