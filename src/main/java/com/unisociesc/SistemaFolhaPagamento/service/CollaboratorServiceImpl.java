package com.unisociesc.SistemaFolhaPagamento.service;

import com.unisociesc.SistemaFolhaPagamento.dto.CollaboratorRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.CollaboratorResponseDto;
import com.unisociesc.SistemaFolhaPagamento.exception.ResourceNotFoundException;
import com.unisociesc.SistemaFolhaPagamento.mapper.CollaboratorMapper;
import com.unisociesc.SistemaFolhaPagamento.model.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.repository.CollaboratorRepository;
import com.unisociesc.SistemaFolhaPagamento.strategy.SalaryStrategyCalculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollaboratorServiceImpl implements CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;
    private final CollaboratorMapper collaboratorMapper;
    private final List<SalaryStrategyCalculator> strategies;

    @Value("${empresa.config.salario-base}")
    private Double baseSalary;

    public CollaboratorServiceImpl(
            CollaboratorRepository collaboratorRepository,
            CollaboratorMapper collaboratorMapper,
            List<SalaryStrategyCalculator> strategies
    ) {
        this.collaboratorRepository = collaboratorRepository;
        this.collaboratorMapper = collaboratorMapper;
        this.strategies = strategies;
    }

    @Override
    public CollaboratorResponseDto register(
            CollaboratorRequestDto requestDto
    ) {
        Collaborator entity = collaboratorMapper.toEntity(requestDto);
        Collaborator savedEntity = collaboratorRepository.save(entity);
        return calculateAndMap(savedEntity);
    }

    @Override
    public List<CollaboratorResponseDto> listAll() {
        return collaboratorRepository.findAll().stream()
                .map(this::calculateAndMap)
                .collect(Collectors.toList());
    }

    @Override
    public CollaboratorResponseDto update(
            CollaboratorRequestDto requestDto,
            Long id
    ) {
        if (!collaboratorRepository.existsById(id)) {
            throw new ResourceNotFoundException("No collaborator found with ID: " + id);
        }

        Collaborator entityToUpdate = collaboratorMapper.toEntity(requestDto);
        entityToUpdate.setId(id);

        Collaborator updatedEntity = collaboratorRepository.save(entityToUpdate);

        return calculateAndMap(updatedEntity);
    }

    @Override
    public void delete(
            Long id
    ) {
        if (!collaboratorRepository.existsById(id)) {
            throw new ResourceNotFoundException("No collaborator found to delete with ID: " + id);
        }
        collaboratorRepository.deleteById(id);
    }

    private CollaboratorResponseDto calculateAndMap(
            Collaborator collaborator
    ) {
        Double finalSalary = strategies.stream()
                .filter(strategy -> strategy.support(collaborator))
                .findFirst()
                .map(strategy -> strategy.calculate(collaborator, baseSalary))
                .orElse(baseSalary);

        Double extra = finalSalary - baseSalary;

        return collaboratorMapper.toResponse(collaborator, baseSalary, extra, finalSalary);
    }
}