package com.unisociesc.SistemaFolhaPagamento.application.usecase;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorRequestDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorResponseDto;
import com.unisociesc.SistemaFolhaPagamento.domain.exception.ResourceNotFoundException;
import com.unisociesc.SistemaFolhaPagamento.application.mapper.CollaboratorMapper;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.contract.CollaboratorUseCase;
import com.unisociesc.SistemaFolhaPagamento.infra.repository.CollaboratorRepository;
import com.unisociesc.SistemaFolhaPagamento.domain.strategy.SalaryCalculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Interactor (use case implementation) for collaborator management in the payroll system.
 *
 * <p>This class implements the {@link CollaboratorUseCase} contract and acts as the
 * application layer orchestrator, coordinating the repository, mapper, and salary
 * calculation strategies to fulfill each use case.</p>
 *
 * <p>The base salary is injected from the application configuration property
 * {@code empresa.config.salario-base} and used as the starting point for all
 * salary calculations.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see CollaboratorUseCase
 * @see SalaryCalculator
 * @see CollaboratorMapper
 */
@Service
public class CollaboratorInteractor implements CollaboratorUseCase {

    private final CollaboratorRepository collaboratorRepository;
    private final CollaboratorMapper collaboratorMapper;
    private final List<SalaryCalculator> strategies;

    /**
     * Base salary injected from the application configuration.
     * Property key: {@code empresa.config.salario-base}.
     */
    @Value("${empresa.config.salario-base}")
    private Double baseSalary;

    /**
     * Constructs a {@code CollaboratorInteractor} with its required dependencies.
     *
     * @param collaboratorRepository the repository used to persist and retrieve collaborators
     * @param collaboratorMapper     the mapper used to convert between DTOs and entities
     * @param strategies             the list of salary calculation strategies available at runtime
     */
    public CollaboratorInteractor(
            CollaboratorRepository collaboratorRepository,
            CollaboratorMapper collaboratorMapper,
            List<SalaryCalculator> strategies
    ) {
        this.collaboratorRepository = collaboratorRepository;
        this.collaboratorMapper = collaboratorMapper;
        this.strategies = strategies;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Converts the request DTO to an entity, persists it, and returns
     * the response DTO with the calculated salary.</p>
     */
    @Override
    public CollaboratorResponseDto register(CollaboratorRequestDto requestDto) {
        Collaborator entity = collaboratorMapper.toEntity(requestDto);
        Collaborator savedEntity = collaboratorRepository.save(entity);
        return calculateAndMap(savedEntity);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Retrieves all collaborators from the repository, calculates each
     * collaborator's salary, and returns the mapped response DTOs.</p>
     */
    @Override
    public List<CollaboratorResponseDto> listAll() {
        return collaboratorRepository.findAll().stream()
                .map(this::calculateAndMap)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     *
     * <p>Verifies the existence of the collaborator before updating. Sets the
     * provided ID on the mapped entity to ensure the correct record is overwritten.</p>
     *
     * @throws ResourceNotFoundException if no collaborator is found with the given ID
     */
    @Override
    public CollaboratorResponseDto update(CollaboratorRequestDto requestDto, Long id) {
        if (!collaboratorRepository.existsById(id)) {
            throw new ResourceNotFoundException("No collaborator found with ID: " + id);
        }

        Collaborator entityToUpdate = collaboratorMapper.toEntity(requestDto);
        entityToUpdate.setId(id);

        Collaborator updatedEntity = collaboratorRepository.save(entityToUpdate);

        return calculateAndMap(updatedEntity);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Verifies the existence of the collaborator before deletion.</p>
     *
     * @throws ResourceNotFoundException if no collaborator is found with the given ID
     */
    @Override
    public void delete(Long id) {
        if (!collaboratorRepository.existsById(id)) {
            throw new ResourceNotFoundException("No collaborator found to delete with ID: " + id);
        }
        collaboratorRepository.deleteById(id);
    }

    /**
     * Calculates the final salary for the given collaborator and maps the result to a response DTO.
     *
     * <p>Iterates through the available {@link SalaryCalculator} strategies and applies
     * the first one that supports the collaborator type. If no strategy matches,
     * the base salary is used as the final salary.</p>
     *
     * @param collaborator the collaborator whose salary will be calculated
     * @return a {@link CollaboratorResponseDto} containing the base salary, extras, and final salary
     */
    private CollaboratorResponseDto calculateAndMap(Collaborator collaborator) {
        Double finalSalary = strategies.stream()
                .filter(strategy -> strategy.support(collaborator))
                .findFirst()
                .map(strategy -> strategy.calculate(collaborator, baseSalary))
                .orElse(baseSalary);

        Double extra = finalSalary - baseSalary;

        return collaboratorMapper.toResponse(collaborator, baseSalary, extra, finalSalary);
    }
}