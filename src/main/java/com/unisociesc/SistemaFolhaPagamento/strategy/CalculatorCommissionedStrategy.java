package com.unisociesc.SistemaFolhaPagamento.strategy;

import com.unisociesc.SistemaFolhaPagamento.model.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.model.CommissionedContributor;
import org.springframework.stereotype.Component;

@Component
public class CalculatorCommissionedStrategy implements SalaryStrategyCalculator {

    @Override
    public boolean support(
            Collaborator collaborator
    ){
        return collaborator instanceof CommissionedContributor;
    }

    @Override
    public Double calculate(
            Collaborator collaborator,
            Double baseSalary
    ){
        CommissionedContributor colab = (CommissionedContributor) collaborator;
        return baseSalary + (colab.getTotalSales()) * (colab.getPercentageCommission());
    }

}
