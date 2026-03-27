package com.unisociesc.SistemaFolhaPagamento.domain.strategy;

import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;

/**
 * Strategy interface for salary calculation.
 *
 * <p>Each implementation is responsible for handling a specific type of
 * {@link Collaborator} and applying the corresponding salary calculation rule.</p>
 *
 * <p>This interface follows the <strong>Strategy</strong> design pattern,
 * allowing different salary calculation algorithms to be selected at runtime
 * based on the collaborator type.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see Collaborator
 */
public interface SalaryCalculator {

    /**
     * Determines whether this strategy supports the given collaborator type.
     *
     * @param collaborator the collaborator to be evaluated
     * @return {@code true} if this strategy can handle the given collaborator, {@code false} otherwise
     */
    boolean support(Collaborator collaborator);

    /**
     * Calculates the final salary for the given collaborator.
     *
     * @param collaborator the collaborator whose salary will be calculated
     * @param baseSalary   the base salary defined in the application configuration
     * @return the final calculated salary
     */
    Double calculate(Collaborator collaborator, Double baseSalary);

}
