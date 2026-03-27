package com.unisociesc.SistemaFolhaPagamento.application.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.unisociesc.SistemaFolhaPagamento.application.dto.standard.request.StandardRequestDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.commissioned.request.CommissionedRequestDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.production.request.ProductionRequestDto;

/**
 * Interface representing the request data for creating or updating a collaborator.
 *
 * <p>Uses Jackson polymorphic deserialization via the {@code bond_type} field
 * to determine the concrete request type at runtime:</p>
 * <ul>
 *   <li>{@code STANDARD} → {@link StandardRequestDto}</li>
 *   <li>{@code COMMISSIONED} → {@link CommissionedRequestDto}</li>
 *   <li>{@code PRODUCTION} → {@link ProductionRequestDto}</li>
 * </ul>
 *
 * <p>All implementations must provide the common collaborator fields defined here,
 * and may include additional type-specific fields such as sales data or production metrics.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see StandardRequestDto
 * @see CommissionedRequestDto
 * @see ProductionRequestDto
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "bond_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StandardRequestDto.class, name = "STANDARD"),
        @JsonSubTypes.Type(value = CommissionedRequestDto.class, name = "COMMISSIONED"),
        @JsonSubTypes.Type(value = ProductionRequestDto.class, name = "PRODUCTION")
})
public interface CollaboratorRequestDto {

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

}