package com.unisociesc.SistemaFolhaPagamento.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("PRODUCTION")
@Getter
@Setter
@NoArgsConstructor
public class ProductionCollaborator extends Collaborator{

    @Column
    private Double valuePerPiece;
    @Column
    private Integer quantityProduced;

    public ProductionCollaborator(Long id, Integer registrationNumber, String name, Double valuePerPiece, Integer quantityProduced) {
        super(id, registrationNumber, name);
        this.valuePerPiece = valuePerPiece;
        this.quantityProduced = quantityProduced;
    }

    public ProductionCollaborator(Double valuePerPiece, Integer quantityProduced) {
        this.valuePerPiece = valuePerPiece;
        this.quantityProduced = quantityProduced;
    }
}
