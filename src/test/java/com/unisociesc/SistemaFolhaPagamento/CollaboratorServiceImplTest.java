package com.unisociesc.SistemaFolhaPagamento;

import com.unisociesc.SistemaFolhaPagamento.dto.standard.request.StandardRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.standard.response.StandardResponseDto;
import com.unisociesc.SistemaFolhaPagamento.dto.commissioned.request.CommissionedRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.commissioned.response.CommissionedResponseDto;
import com.unisociesc.SistemaFolhaPagamento.exception.ResourceNotFoundException;
import com.unisociesc.SistemaFolhaPagamento.mapper.CollaboratorMapper;
import com.unisociesc.SistemaFolhaPagamento.model.CommissionedContributor;
import com.unisociesc.SistemaFolhaPagamento.model.StandardContributor;
import com.unisociesc.SistemaFolhaPagamento.repository.CollaboratorRepository;
import com.unisociesc.SistemaFolhaPagamento.service.CollaboratorServiceImpl;
import com.unisociesc.SistemaFolhaPagamento.strategy.SalaryStrategyCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CollaboratorServiceImplTest {

    @Mock
    private CollaboratorRepository collaboratorRepository;

    @Mock
    private CollaboratorMapper collaboratorMapper;

    @Mock
    private SalaryStrategyCalculator strategyStandard;

    @Mock
    private SalaryStrategyCalculator strategyCommissioned;

    @InjectMocks
    private CollaboratorServiceImpl service;

    private final Double BASE_SALARY = 3000.0;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
                service,
                "baseSalary",
                BASE_SALARY
        );
        ReflectionTestUtils.setField(
                service,
                "strategies",
                List.of(strategyStandard, strategyCommissioned)
        );
    }

    @Test
    @DisplayName("Should save collaborator and return ResponseDto with calculated salary")
    void shouldSaveAndReturnResponseDtoWithCalculatedSalary() {
        StandardRequestDto requestDto = new StandardRequestDto(
                1,
                "João"
        );
        StandardContributor entity = new StandardContributor(
                null,
                1,
                "João"
        );
        StandardContributor saved = new StandardContributor(
                1L,
                1,
                "João"
        );
        StandardResponseDto expectedResponse = new StandardResponseDto(
                "João",
                1,
                BASE_SALARY,
                0.0, BASE_SALARY
        );

        when(collaboratorMapper.toEntity(requestDto)).thenReturn(entity);
        when(collaboratorRepository.save(entity)).thenReturn(saved);
        when(strategyStandard.support(saved)).thenReturn(true);
        when(strategyStandard.calculate(saved, BASE_SALARY)).thenReturn(BASE_SALARY);
        when(collaboratorMapper.toResponse(saved, BASE_SALARY, 0.0, BASE_SALARY)).thenReturn(expectedResponse);

        var result = service.register(requestDto);

        assertNotNull(result);
        assertEquals("João", result.name());
        assertEquals(BASE_SALARY, result.finalSalary());
        verify(collaboratorRepository).save(entity);
    }

    @Test
    @DisplayName("Should use BASE SALARY as fallback when no strategy supports the collaborator")
    void shouldRegisterWithBaseSalaryWhenNoStrategySupportsCollaborator() {
        StandardRequestDto requestDto = new StandardRequestDto(
                1,
                "João"
        );
        StandardContributor entity = new StandardContributor(
                null,
                1,
                "João"
        );
        StandardContributor saved = new StandardContributor(
                1L,
                1,
                "João"
        );
        StandardResponseDto expectedResponse = new StandardResponseDto(
                "João",
                1,
                BASE_SALARY,
                0.0, BASE_SALARY
        );

        when(collaboratorMapper.toEntity(requestDto)).thenReturn(entity);
        when(collaboratorRepository.save(entity)).thenReturn(saved);
        when(strategyStandard.support(saved)).thenReturn(false);
        when(strategyCommissioned.support(saved)).thenReturn(false);
        when(collaboratorMapper.toResponse(saved, BASE_SALARY, 0.0, BASE_SALARY)).thenReturn(expectedResponse);

        var result = service.register(requestDto);

        assertEquals(BASE_SALARY, result.finalSalary());
    }

    @Test
    @DisplayName("Should return a list with ALL collaborators correctly mapped")
    void shouldReturnListWithAllCollaboratorsMapped() {
        StandardContributor c1 = new StandardContributor(
                1L,
                1,
                "João"
        );
        CommissionedContributor c2 = new CommissionedContributor(
                2L,
                2,
                "Victoria",
                5000.0,
                10.0
        );
        StandardResponseDto r1 = new StandardResponseDto(
                "João",
                1,
                BASE_SALARY,
                0.0, BASE_SALARY
        );
        CommissionedResponseDto r2 = new CommissionedResponseDto(
                "Victoria",
                2,
                BASE_SALARY,
                500.0,
                3500.0
        );

        when(collaboratorRepository.findAll()).thenReturn(List.of(c1, c2));
        when(strategyStandard.support(c1)).thenReturn(true);
        when(strategyStandard.calculate(c1, BASE_SALARY)).thenReturn(BASE_SALARY);
        when(collaboratorMapper.toResponse(c1, BASE_SALARY, 0.0, BASE_SALARY)).thenReturn(r1);
        when(strategyStandard.support(c2)).thenReturn(false);
        when(strategyCommissioned.support(c2)).thenReturn(true);
        when(strategyCommissioned.calculate(c2, BASE_SALARY)).thenReturn(3500.0);
        when(collaboratorMapper.toResponse(c2, BASE_SALARY, 500.0, 3500.0)).thenReturn(r2);

        var result = service.listAll();

        assertEquals(2, result.size());
        verify(collaboratorRepository).findAll();
    }

    @Test
    @DisplayName("Should return an EMPTY list when no collaborators exist")
    void shouldReturnEmptyListWhenNoCollaboratorsExist() {
        when(collaboratorRepository.findAll()).thenReturn(List.of());

        var result = service.listAll();

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should successfully update and return ResponseDto when ID exists")
    void shouldUpdateAndReturnResponseDtoWhenIdExists() {
        Long id = 1L;
        CommissionedRequestDto requestDto = new CommissionedRequestDto(
                2,
                "Victoria",
                8000.0,
                10.0
        );
        CommissionedContributor entity = new CommissionedContributor(
                null,
                2,
                "Victoria",
                8000.0,
                10.0
        );
        CommissionedContributor updated = new CommissionedContributor(
                id,
                2,
                "Victoria",
                8000.0,
                10.0
        );
        CommissionedResponseDto expectedResponse = new CommissionedResponseDto(
                "Victoria",
                2,
                BASE_SALARY,
                800.0,
                3800.0
        );

        when(collaboratorRepository.existsById(id)).thenReturn(true);
        when(collaboratorMapper.toEntity(requestDto)).thenReturn(entity);
        when(collaboratorRepository.save(entity)).thenReturn(updated);
        when(strategyCommissioned.support(updated)).thenReturn(true);
        when(strategyCommissioned.calculate(updated, BASE_SALARY)).thenReturn(3800.0);
        when(collaboratorMapper.toResponse(updated, BASE_SALARY, 800.0, 3800.0)).thenReturn(expectedResponse);

        var result = service.update(requestDto, id);

        assertNotNull(result);
        assertEquals("Victoria", result.name());
        verify(collaboratorRepository).save(entity);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when ID does not exist")
    void shouldThrowExceptionWhenUpdatingNonExistentId() {
        Long id = 99L;
        CommissionedRequestDto requestDto = new CommissionedRequestDto(
                2,
                "Victoria",
                5000.0,
                10.0
        );

        when(collaboratorRepository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.update(requestDto, id));
        verify(collaboratorRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should DELETE collaborator when ID exists")
    void shouldDeleteWhenIdExists() {
        Long id = 1L;
        when(collaboratorRepository.existsById(id)).thenReturn(true);

        assertDoesNotThrow(() -> service.delete(id));

        verify(collaboratorRepository).deleteById(id);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when ID does not exist")
    void shouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Long id = 99L;
        when(collaboratorRepository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.delete(id));
        verify(collaboratorRepository, never()).deleteById(any());
    }
}