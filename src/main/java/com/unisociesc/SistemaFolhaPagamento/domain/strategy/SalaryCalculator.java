package com.unisociesc.SistemaFolhaPagamento.domain.strategy;

import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;

public interface SalaryCalculator {

    boolean support(Collaborator collaborator);

    Double calculate(Collaborator collaborator, Double baseSalary);

}
