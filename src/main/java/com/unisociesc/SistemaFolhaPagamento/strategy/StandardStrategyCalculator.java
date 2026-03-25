package com.unisociesc.SistemaFolhaPagamento.strategy;

import com.unisociesc.SistemaFolhaPagamento.model.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.model.StandardContributor;
import org.springframework.stereotype.Component;

@Component
public class StandardStrategyCalculator implements SalaryStrategyCalculator{

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
