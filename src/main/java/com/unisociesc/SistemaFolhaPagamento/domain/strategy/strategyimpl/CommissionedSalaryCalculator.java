package com.unisociesc.SistemaFolhaPagamento.domain.strategy.strategyimpl;

import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.CommissionedContributor;
import com.unisociesc.SistemaFolhaPagamento.domain.strategy.SalaryCalculator;
import org.springframework.stereotype.Component;

/**
 * Salary calculation strategy for {@link CommissionedContributor} collaborators.
 *
 * <p>The final salary is calculated by adding a commission to the base salary.
 * The commission is based on the total sales amount and the commission percentage
 * defined for the collaborator:</p>
 *
 * <pre>
 *   finalSalary = baseSalary + (totalSales * percentageCommission / 100)
 * </pre>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see SalaryCalculator
 * @see CommissionedContributor
 */
@Component
public class CommissionedSalaryCalculator implements SalaryCalculator {

    /**
     * Checks whether this strategy supports the given collaborator.
     *
     * @param collaborator the collaborator to be evaluated
     * @return {@code true} if the collaborator is an instance of {@link CommissionedContributor},
     *         {@code false} otherwise
     */
    @Override
    public boolean support(Collaborator collaborator) {
        return collaborator instanceof CommissionedContributor;
    }

    /**
     * Calculates the final salary for a commissioned collaborator.
     *
     * <p>The commission is computed as a percentage of total sales and added to the base salary.</p>
     *
     * @param collaborator the collaborator, expected to be a {@link CommissionedContributor}
     * @param baseSalary   the base salary defined in the application configuration
     * @return the final salary including the commission amount
     */
    @Override
    public Double calculate(Collaborator collaborator, Double baseSalary) {
        CommissionedContributor colab = (CommissionedContributor) collaborator;
        return baseSalary + (colab.getTotalSales()) * (colab.getPercentageCommission()) / 100;
    }

}
