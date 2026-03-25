package com.unisociesc.SistemaFolhaPagamento.strategy;

import com.unisociesc.SistemaFolhaPagamento.model.Collaborator;

public interface SalaryStrategyCalculator {

    boolean support(Collaborator collaborator);

    Double calculate(Collaborator collaborator, Double baseSalary);

}
