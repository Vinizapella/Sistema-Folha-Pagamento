package com.unisociesc.SistemaFolhaPagamento.strategy;

import com.unisociesc.SistemaFolhaPagamento.model.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.model.ProductionCollaborator;
import org.springframework.stereotype.Component;

@Component
public class ProductStrategyCalculator implements SalaryStrategyCalculator{

    @Override
    public boolean support(
            Collaborator collaborator
    ){
        return collaborator instanceof ProductionCollaborator;
    }

    @Override
    public Double calculate(
            Collaborator collaborator,
            Double baseSalary
    ){
        ProductionCollaborator product = (ProductionCollaborator) collaborator;
        return baseSalary + (product.getValuePerPiece()) * (product.getQuantityProduced()) / 100;
    }

}
