package com.unisociesc.SistemaFolhaPagamento.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("COMMISSIONED")
public class CommissionedContributor extends Collaborator{

    @Column
    private Double totalSales;
    @Column
    private Double percentageCommission;

    public CommissionedContributor(Long id, Integer registrationNumber, String name, Double totalSales, Double percentageCommission) {
        super(id, registrationNumber, name);
        this.totalSales = totalSales;
        this.percentageCommission = percentageCommission;
    }
}
