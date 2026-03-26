package com.unisociesc.SistemaFolhaPagamento.domain.strategy.strategyimpl;

import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.entity.ProductionCollaborator;
import com.unisociesc.SistemaFolhaPagamento.domain.strategy.SalaryCalculator;
import org.springframework.stereotype.Component;

@Component
public class ProductionSalaryCalculator implements SalaryCalculator {

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
        return baseSalary + (product.getValuePerPiece()) * (product.getQuantityProduced());
    }

}
