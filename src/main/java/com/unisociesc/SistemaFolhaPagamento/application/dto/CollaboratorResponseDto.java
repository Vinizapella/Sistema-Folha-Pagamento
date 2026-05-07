package com.unisociesc.SistemaFolhaPagamento.application.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.unisociesc.SistemaFolhaPagamento.application.dto.commissioned.response.CommissionedResponseDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.production.response.ProductionResponseDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.standard.response.StandardResponseDto;

/**
 * Interface representing the response data for a collaborator in the payroll system.
 *
 * <p>Uses Jackson polymorphic deserialization via the {@code bond_type} field
 * to determine the concrete response type at runtime:</p>
 * <ul>
 *   <li>{@code STANDARD} → {@link StandardResponseDto}</li>
 *   <li>{@code COMMISSIONED} → {@link CommissionedResponseDto}</li>
 *   <li>{@code PRODUCTION} → {@link ProductionResponseDto}</li>
 * </ul>
 *
 * <p>All implementations must expose the common collaborator fields defined here,
 * and may include additional type-specific fields such as commission or production extras.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see StandardResponseDto
 * @see CommissionedResponseDto
 * @see ProductionResponseDto
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "bond_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StandardResponseDto.class, name = "STANDARD"),
        @JsonSubTypes.Type(value = CommissionedResponseDto.class, name = "COMMISSIONED"),
        @JsonSubTypes.Type(value = ProductionResponseDto.class, name = "PRODUCTION")
})
public interface CollaboratorResponseDto {

    /**
     * Returns the unique identifier of the collaborator.
     *
     * @return the collaborator's ID
     */
    Long id();

    /**
     * Returns the unique registration number of the collaborator.
     *
     * @return the registration number
     */
    Integer registrationNumber();

    /**
     * Returns the full name of the collaborator.
     *
     * @return the collaborator's name
     */
    String name();

    /**
     * Returns the base salary configured for the company.
     *
     * @return the base salary
     */
    Double baseSalary();

    /**
     * Returns the final calculated salary, including any bonuses or commissions.
     *
     * @return the final salary
     */
    Double finalSalary();

}