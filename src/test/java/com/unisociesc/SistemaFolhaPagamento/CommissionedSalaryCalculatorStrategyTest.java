package com.unisociesc.SistemaFolhaPagamento;

import com.unisociesc.SistemaFolhaPagamento.domain.entity.CommissionedContributor;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.ProductionCollaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.StandardContributor;
import com.unisociesc.SistemaFolhaPagamento.domain.strategy.strategyimpl.CommissionedSalaryCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommissionedSalaryCalculatorStrategyTest {

    private CommissionedSalaryCalculator strategy;

    @BeforeEach
    void setUp() {
        strategy = new CommissionedSalaryCalculator();
    }

    @Test
    @DisplayName("Should support Commissioned Contributor type")
    void shouldReturnTrueForCommissionedContributor() {
        CommissionedContributor collaborator = new CommissionedContributor(
                1L,
                2,
                "Victoria",
                5000.0,
                10.0
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
    @DisplayName("Should return base salary + (total sales * commission percentage / 100)")
    void shouldCalculateTotalSalaryByAddingBaseSalaryAndCommission() {
        CommissionedContributor collaborator = new CommissionedContributor(
                1L,
                2,
                "Victoria",
                5000.0,
                10.0
        );
        Double baseSalary = 3000.0;

        Double result = strategy.calculate(collaborator, baseSalary);

        assertEquals(3500.0, result);
    }

    @Test
    @DisplayName("Should return ONLY base salary when total sales is zero")
    void shouldReturnOnlyBaseSalaryWhenTotalSalesIsZero() {
        CommissionedContributor collaborator = new CommissionedContributor(
                1L,
                2,
                "Victoria",
                0.0,
                10.0
        );
        Double baseSalary = 3000.0;

        Double result = strategy.calculate(collaborator, baseSalary);

        assertEquals(3000.0, result);
    }

    @Test
    @DisplayName("Should return ONLY base salary when commission percentage is ZERO")
    void shouldReturnOnlyBaseSalaryWhenCommissionPercentageIsZero() {
        CommissionedContributor collaborator = new CommissionedContributor(
                1L,
                2,
                "Victoria",
                5000.0,
                0.0
        );
        Double baseSalary = 3000.0;

        Double result = strategy.calculate(collaborator, baseSalary);

        assertEquals(3000.0, result);
    }

    @Test
    @DisplayName("Should calculate correctly when commission percentage is 100%")
    void shouldCalculateCorrectlyWithFullCommissionPercentage() {
        CommissionedContributor collaborator = new CommissionedContributor(
                1L,
                2,
                "Victoria",
                1000.0,
                100.0
        );
        Double baseSalary = 2000.0;

        Double result = strategy.calculate(collaborator, baseSalary);

        assertEquals(3000.0, result);
    }
}

