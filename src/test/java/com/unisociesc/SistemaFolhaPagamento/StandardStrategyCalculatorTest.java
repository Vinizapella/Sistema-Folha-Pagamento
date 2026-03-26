package com.unisociesc.SistemaFolhaPagamento;


import com.unisociesc.SistemaFolhaPagamento.domain.entity.CommissionedContributor;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.ProductionCollaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.StandardContributor;
import com.unisociesc.SistemaFolhaPagamento.domain.strategy.strategyimpl.StandardSalaryCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardStrategyCalculatorTest {

    private StandardSalaryCalculator strategy;

    @BeforeEach
    void setUp() {
        strategy = new StandardSalaryCalculator();
    }

    @Test
    @DisplayName("Should support Standard Contributor type")
    void supportReturnsTrueForStandardContributor() {
        StandardContributor collaborator = new StandardContributor(
                1L,
                1,
                "João"
        );
        assertTrue(strategy.support(collaborator));
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
    @DisplayName("Should NOT support Production Collaborator type")
    void shouldReturnFalseForProductionCollaborator() {
        ProductionCollaborator collaborator = new ProductionCollaborator(
                1L,
                3,
                "Carlos",
                15.0,
                100
        );
        assertFalse(strategy.support(collaborator));
    }

    @Test
    @DisplayName("Should return EXACTLY the base salary")
    void shouldReturnExactlyBaseSalary() {
        StandardContributor collaborator = new StandardContributor(
                1L,
                1,
                "João"
        );
        Double baseSalary = 3000.0;

        Double result = strategy.calculate(collaborator, baseSalary);

        assertEquals(3000.0, result);
    }

    @Test
    @DisplayName("Should return ZERO when base salary is zero")
    void shouldReturnZeroWhenBaseSalaryIsZero() {
        StandardContributor collaborator = new StandardContributor(
                1L,
                1,
                "João"
        );

        Double result = strategy.calculate(collaborator, 0.0);

        assertEquals(0.0, result);
    }
}
