package com.unisociesc.SistemaFolhaPagamento.domain.strategy.strategyimpl;

import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.StandardContributor;
import com.unisociesc.SistemaFolhaPagamento.domain.strategy.SalaryCalculator;
import org.springframework.stereotype.Component;

/**
 * Salary calculation strategy for {@link StandardContributor} collaborators.
 *
 * <p>Standard collaborators receive no bonuses or commissions beyond the base salary.
 * The final salary is always equal to the configured base salary:</p>
 *
 * <pre>
 *   finalSalary = baseSalary
 * </pre>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see SalaryCalculator
 * @see StandardContributor
 */
@Component
public class StandardSalaryCalculator implements SalaryCalculator {

    /**
     * Checks whether this strategy supports the given collaborator.
     *
     * @param collaborator the collaborator to be evaluated
     * @return {@code true} if the collaborator is an instance of {@link StandardContributor},
     *         {@code false} otherwise
     */
    @Override
    public boolean support(Collaborator collaborator) {
        return collaborator instanceof StandardContributor;
    }

    /**
     * Returns the base salary as the final salary for a standard collaborator.
     *
     * <p>No additional bonuses or deductions are applied.</p>
     *
     * @param collaborator the collaborator (not used in the calculation)
     * @param baseSalary   the base salary defined in the application configuration
     * @return the base salary unchanged
     */
    @Override
    public Double calculate(Collaborator collaborator, Double baseSalary) {
        return baseSalary;
    }

}