package com.unisociesc.SistemaFolhaPagamento.domain.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("STANDARD")
@Getter
@Setter
public class StandardContributor extends Collaborator{

    public StandardContributor(Long id, Integer registrationNumber, String name) {
        super(id, registrationNumber, name);
    }

    public StandardContributor() {
    }
}
