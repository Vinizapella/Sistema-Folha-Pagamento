package com.unisociesc.SistemaFolhaPagamento.domain.strategy.strategyimpl;

import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.StandardContributor;
import com.unisociesc.SistemaFolhaPagamento.domain.strategy.SalaryCalculator;
import org.springframework.stereotype.Component;

@Component
public class StandardSalaryCalculator implements SalaryCalculator {

    @Override
    public boolean support(
            Collaborator collaborator
    ){
        return collaborator instanceof StandardContributor;
    }

    public Double calculate(
            Collaborator collaborator,
            Double baseSalary
    ){
        return baseSalary;
    }

}
