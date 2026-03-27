package com.unisociesc.SistemaFolhaPagamento.domain.strategy.strategyimpl;

import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.ProductionCollaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.strategy.SalaryCalculator;
import org.springframework.stereotype.Component;

/**
 * Salary calculation strategy for {@link ProductionCollaborator} collaborators.
 *
 * <p>The final salary is calculated by adding a production bonus to the base salary.
 * The bonus is based on the number of pieces produced and the value assigned per piece:</p>
 *
 * <pre>
 *   finalSalary = baseSalary + (valuePerPiece * quantityProduced)
 * </pre>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see SalaryCalculator
 * @see ProductionCollaborator
 */
@Component
public class ProductionSalaryCalculator implements SalaryCalculator {

    /**
     * Checks whether this strategy supports the given collaborator.
     *
     * @param collaborator the collaborator to be evaluated
     * @return {@code true} if the collaborator is an instance of {@link ProductionCollaborator},
     *         {@code false} otherwise
     */
    @Override
    public boolean support(Collaborator collaborator) {
        return collaborator instanceof ProductionCollaborator;
    }

    /**
     * Calculates the final salary for a production-based collaborator.
     *
     * <p>The production bonus is computed by multiplying the value per piece
     * by the total quantity produced, then added to the base salary.</p>
     *
     * @param collaborator the collaborator, expected to be a {@link ProductionCollaborator}
     * @param baseSalary   the base salary defined in the application configuration
     * @return the final salary including the production bonus
     */
    @Override
    public Double calculate(Collaborator collaborator, Double baseSalary) {
        ProductionCollaborator product = (ProductionCollaborator) collaborator;
        return baseSalary + (product.getValuePerPiece()) * (product.getQuantityProduced());
    }

}