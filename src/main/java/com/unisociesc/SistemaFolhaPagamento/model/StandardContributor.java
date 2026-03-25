package com.unisociesc.SistemaFolhaPagamento.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
