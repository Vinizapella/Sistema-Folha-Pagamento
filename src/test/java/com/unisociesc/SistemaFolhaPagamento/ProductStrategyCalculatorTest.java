package com.unisociesc.SistemaFolhaPagamento;


import com.unisociesc.SistemaFolhaPagamento.model.CommissionedContributor;
import com.unisociesc.SistemaFolhaPagamento.model.ProductionCollaborator;
import com.unisociesc.SistemaFolhaPagamento.model.StandardContributor;
import com.unisociesc.SistemaFolhaPagamento.strategy.ProductStrategyCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductStrategyCalculatorTest {

    private ProductStrategyCalculator strategy;

    @BeforeEach
    void setUp() {
        strategy = new ProductStrategyCalculator();
    }

    @Test
    @DisplayName("Should support Production Collaborator type")
    void shouldReturnTrueForProductionCollaborator() {
        ProductionCollaborator collaborator = new ProductionCollaborator(
                1L,
                3,
                "Carlos",
                15.0,
                100
        );
        assertTrue(strategy.support(collaborator));
    }

    @Test
    @DisplayName("Should NOT support Standard Contributor type")
    void shouldReturnFalseForStandardContributor() {
        StandardContributor collaborator = new StandardContributor(
                1L,
                1,
                "João"
        );
        assertFalse(strategy.support(collaborator));
    }

    @Test
    @DisplayName("Should NOT support Commissioned Contributor type")
    void shouldReturnFalseForCommissionedContributor() {
        CommissionedContributor collaborator = new CommissionedContributor(
                1L,
                2,
                "Victoria",
                5000.0,
                10.0
        );
        assertFalse(strategy.support(collaborator));
    }

    @Test
    @DisplayName("Should return base salary + (rate per piece * quantity produced)")
    void shouldCalculateTotalSalaryByAddingBaseSalaryAndProductionValue() {
        ProductionCollaborator collaborator = new ProductionCollaborator(
                1L,
                3,
                "Carlos",
                15.0,
                100
        );
        Double baseSalary = 3000.0;

        Double result = strategy.calculate(collaborator, baseSalary);

        assertEquals(4500.0, result);
    }

    @Test
    @DisplayName("Should return ONLY base salary when production quantity is zero")
    void shouldReturnOnlyBaseSalaryWhenProductionQuantityIsZero() {
        ProductionCollaborator collaborator = new ProductionCollaborator(
                1L,
                3,
                "Carlos",
                15.0,
                0
        );
        Double baseSalary = 3000.0;

        Double result = strategy.calculate(collaborator, baseSalary);

        assertEquals(3000.0, result);
    }

    @Test
    @DisplayName("Should return ONLY base salary when rate per piece is ZERO")
    void shouldReturnOnlyBaseSalaryWhenRatePerPieceIsZero() {
        ProductionCollaborator collaborator = new ProductionCollaborator(
                1L,
                3,
                "Carlos",
                0.0,
                100
        );
        Double baseSalary = 3000.0;

        Double result = strategy.calculate(collaborator, baseSalary);

        assertEquals(3000.0, result);
    }
}