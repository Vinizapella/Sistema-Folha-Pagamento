package com.unisociesc.SistemaFolhaPagamento.infra.repository;

import com.unisociesc.SistemaFolhaPagamento.domain.entity.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for the {@link Collaborator} entity.
 *
 * <p>Automatically inherits CRUD operations from {@link JpaRepository},
 * such as {@code save}, {@code findAll}, {@code findById}, {@code deleteById}
 * and {@code existsById}, with no manual implementation required.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @see JpaRepository
 * @see Collaborator
 * @version 1.0
 * @since 26/03/2026
 */
@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {
}
