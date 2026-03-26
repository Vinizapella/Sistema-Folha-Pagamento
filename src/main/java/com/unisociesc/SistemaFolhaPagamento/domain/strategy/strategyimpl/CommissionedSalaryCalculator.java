package com.unisociesc.SistemaFolhaPagamento.domain.strategy.strategyimpl;

import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.CommissionedContributor;
import com.unisociesc.SistemaFolhaPagamento.domain.strategy.SalaryCalculator;
import org.springframework.stereotype.Component;

@Component
public class CommissionedSalaryCalculator implements SalaryCalculator {

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
        return baseSalary + (colab.getTotalSales()) * (colab.getPercentageCommission()) / 100;
    }

}
