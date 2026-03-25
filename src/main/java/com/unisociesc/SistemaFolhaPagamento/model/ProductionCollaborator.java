package com.unisociesc.SistemaFolhaPagamento.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("PRODUCAO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductionCollaborator extends Collaborator{

    @Column
    private Double valorPorPeca;
    @Column
    private Integer quantidadeProduzida;

}
