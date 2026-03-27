package com.unisociesc.SistemaFolhaPagamento.domain.contract;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorRequestDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorResponseDto;

import java.util.List;

/**
 * Contract defining the use cases for collaborator management.
 *
 * <p>This interface acts as the boundary between the application layer and the domain layer,
 * declaring all operations available for managing collaborators in the payroll system.</p>
 *
 * <p>Implementations are responsible for applying business rules such as salary calculation
 * based on the collaborator type (standard, commissioned or production).</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see CollaboratorRequestDto
 * @see CollaboratorResponseDto
 */
public interface CollaboratorUseCase {

    /**
     * Registers a new collaborator in the payroll system.
     *
     * @param collaboratorRequestDto the data required to create the collaborator
     * @return a {@link CollaboratorResponseDto} containing the registered collaborator's data
     *         along with the calculated salary
     */
    CollaboratorResponseDto register(CollaboratorRequestDto collaboratorRequestDto);

    /**
     * Retrieves all collaborators registered in the payroll system.
     *
     * @return a list of {@link CollaboratorResponseDto} with each collaborator's data
     *         and calculated salary; returns an empty list if no collaborators exist
     */
    List<CollaboratorResponseDto> listAll();

    /**
     * Updates the data of an existing collaborator.
     *
     * @param collaboratorRequestDto the new data to be applied to the collaborator
     * @param id                     the unique identifier of the collaborator to be updated
     * @return a {@link CollaboratorResponseDto} containing the updated collaborator's data
     *         and recalculated salary
     * @throws com.unisociesc.SistemaFolhaPagamento.domain.exception.ResourceNotFoundException
     *         if no collaborator is found with the given ID
     */
    CollaboratorResponseDto update(CollaboratorRequestDto collaboratorRequestDto, Long id);

    /**
     * Removes a collaborator from the payroll system.
     *
     * @param id the unique identifier of the collaborator to be deleted
     * @throws com.unisociesc.SistemaFolhaPagamento.domain.exception.ResourceNotFoundException
     *         if no collaborator is found with the given ID
     */
    void delete(Long id);

}