package com.unisociesc.SistemaFolhaPagamento.infra.repository;

import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {
}
