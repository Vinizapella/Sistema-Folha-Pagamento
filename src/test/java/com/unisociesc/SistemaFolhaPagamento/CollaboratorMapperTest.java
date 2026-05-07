package com.unisociesc.SistemaFolhaPagamento;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorRequestDto;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.application.dto.commissioned.request.CommissionedRequestDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.commissioned.response.CommissionedResponseDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.production.request.ProductionRequestDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.production.response.ProductionResponseDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.standard.request.StandardRequestDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.standard.response.StandardResponseDto;
import com.unisociesc.SistemaFolhaPagamento.application.mapper.CollaboratorMapper;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.CommissionedContributor;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.ProductionCollaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.StandardContributor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorMapperTest {

    private CollaboratorMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CollaboratorMapper();
    }


    @Test
    @DisplayName("Should convert StandardRequestDto into StandardContributor entity")
    void shouldConvertStandardRequestDtoToStandardContributor() {
        StandardRequestDto dto = new StandardRequestDto(
                1,
                "João"
        );

        var entity = mapper.toEntity(dto);

        assertInstanceOf(StandardContributor.class, entity);
        assertEquals(1, entity.getRegistrationNumber());
        assertEquals("João", entity.getName());
        assertNull(entity.getId());
    }

    @Test
    @DisplayName("Should convert CommissionedRequestDto into CommissionedContributor entity")
    void shouldConvertCommissionedRequestDtoToCommissionedContributor() {
        CommissionedRequestDto dto = new CommissionedRequestDto(
                2,
                "Victoria",
                5000.0,
                10.0
        );

        var entity = mapper.toEntity(dto);

        assertInstanceOf(CommissionedContributor.class, entity);
        CommissionedContributor commissioned = (CommissionedContributor) entity;
        assertEquals(2, commissioned.getRegistrationNumber());
        assertEquals("Victoria", commissioned.getName());
        assertEquals(5000.0, commissioned.getTotalSales());
        assertEquals(10.0, commissioned.getPercentageCommission());
    }

    @Test
    @DisplayName("Should convert ProductionRequestDto into ProductionCollaborator entity")
    void shouldConvertProductionRequestDtoToProductionCollaborator() {
        ProductionRequestDto dto = new ProductionRequestDto(
                3,
                "Carlos",
                15.0,
                100
        );

        var entity = mapper.toEntity(dto);

        assertInstanceOf(ProductionCollaborator.class, entity);
        ProductionCollaborator production = (ProductionCollaborator) entity;
        assertEquals(3, production.getRegistrationNumber());
        assertEquals("Carlos", production.getName());
        assertEquals(15.0, production.getValuePerPiece());
        assertEquals(100, production.getQuantityProduced());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when DTO type is UNKNOWN")
    void shouldThrowIllegalArgumentExceptionForUnknownDtoType() {
        var dtoInvalido = new CollaboratorRequestDto() {
            @Override
            public Integer registrationNumber() { return 0; }
            @Override
            public String name() { return "unknown"; }
        };

        assertThrows(IllegalArgumentException.class, () -> mapper.toEntity(dtoInvalido));
    }

    @Test
    @DisplayName("Should return StandardResponseDto with ZERO extra value")
    void shouldReturnStandardResponseDtoWithZeroExtraValue() {
        StandardContributor collaborator = new StandardContributor(
                1L,
                1,
                "João"
        );

        var response = mapper.toResponse(
                collaborator,
                3000.0,
                0.0,
                3000.0
        );

        assertInstanceOf(StandardResponseDto.class, response);
        StandardResponseDto standardResponse = (StandardResponseDto) response;
        assertEquals(1L, standardResponse.id());
        assertEquals("João", standardResponse.name());
        assertEquals(1, standardResponse.registrationNumber());
        assertEquals(3000.0, standardResponse.baseSalary());
        assertEquals(0.0, standardResponse.extras());
        assertEquals(3000.0, standardResponse.finalSalary());
    }

    @Test
    @DisplayName("Should return CommissionedResponseDto with correctly mapped commission value")
    void shouldReturnCommissionedResponseDtoWithCorrectCommissionValue() {
        CommissionedContributor collaborator = new CommissionedContributor(
                1L,
                2,
                "Victoria",
                5000.0,
                10.0
        );

        var response = mapper.toResponse(
                collaborator,
                3000.0,
                500.0,
                3500.0
        );

        assertInstanceOf(CommissionedResponseDto.class, response);
        CommissionedResponseDto commissionedResponse = (CommissionedResponseDto) response;
        assertEquals(1L, commissionedResponse.id());
        assertEquals("Victoria", commissionedResponse.name());
        assertEquals(2, commissionedResponse.registrationNumber());
        assertEquals(3000.0, commissionedResponse.baseSalary());
        assertEquals(500.0, commissionedResponse.extras());
        assertEquals(3500.0, commissionedResponse.finalSalary());
        assertEquals(5000.0, commissionedResponse.totalSales());
        assertEquals(10.0, commissionedResponse.percentageCommission());
    }

    @Test
    @DisplayName("Should return ProductionResponseDto with correctly mapped production value")
    void shouldReturnProductionResponseDtoWithCorrectProductionValue() {
        ProductionCollaborator collaborator = new ProductionCollaborator(
                1L,
                3,
                "Carlos",
                15.0,
                100
        );

        var response = mapper.toResponse(
                collaborator,
                3000.0,
                1500.0,
                4500.0
        );

        assertInstanceOf(ProductionResponseDto.class, response);
        ProductionResponseDto productionResponse = (ProductionResponseDto) response;
        assertEquals(1L, productionResponse.id());
        assertEquals("Carlos", productionResponse.name());
        assertEquals(3, productionResponse.registrationNumber());
        assertEquals(3000.0, productionResponse.baseSalary());
        assertEquals(1500.0, productionResponse.extras());
        assertEquals(4500.0, productionResponse.finalSalary());
        assertEquals(15.0, productionResponse.valuePerPiece());
        assertEquals(100, productionResponse.quantityProduced());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when entity type is UNKNOWN")
    void shouldThrowIllegalArgumentExceptionForUnknownEntity() {
        var entidadeInvalida = new Collaborator(
                1L,
                999,
                "unknown"
        ) {
        };

        assertThrows(IllegalArgumentException.class,
                () -> mapper.toResponse(entidadeInvalida, 3000.0, 0.0, 3000.0));
    }
}