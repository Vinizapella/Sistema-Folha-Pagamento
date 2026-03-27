package com.unisociesc.SistemaFolhaPagamento.domain.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a standard collaborator in the payroll system.
 *
 * <p>A standard collaborator receives no bonuses or commissions beyond the base salary.
 * The final salary is always equal to the configured base salary:</p>
 *
 * <pre>
 *   finalSalary = baseSalary
 * </pre>
 *
 * <p>Stored in the {@code collaborator_table} with discriminator value {@code STANDARD}.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see Collaborator
 */
@Entity
@DiscriminatorValue("STANDARD")
@Getter
@Setter
public class StandardContributor extends Collaborator {

    /**
     * Constructs a {@code StandardContributor} with all fields explicitly provided.
     *
     * @param id                 the unique identifier (use {@code null} for new records)
     * @param registrationNumber the unique registration number of the collaborator
     * @param name               the full name of the collaborator
     */
    public StandardContributor(Long id, Integer registrationNumber, String name) {
        super(id, registrationNumber, name);
    }

    /**
     * Default no-args constructor required by JPA.
     */
    public StandardContributor() {
    }
}