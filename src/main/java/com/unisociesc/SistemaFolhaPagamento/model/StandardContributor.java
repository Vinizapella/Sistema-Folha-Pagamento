package com.unisociesc.SistemaFolhaPagamento.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("PADRAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardContributor extends Collaborator{ }
