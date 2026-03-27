package com.unisociesc.SistemaFolhaPagamento.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a production-based collaborator in the payroll system.
 *
 * <p>A production collaborator receives the base salary plus a bonus calculated
 * from the number of pieces produced and the value assigned per piece:</p>
 *
 * <pre>
 *   finalSalary = baseSalary + (valuePerPiece * quantityProduced)
 * </pre>
 *
 * <p>Stored in the {@code collaborator_table} with discriminator value {@code PRODUCTION}.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see Collaborator
 */
@Entity
@DiscriminatorValue("PRODUCTION")
@Getter
@Setter
@NoArgsConstructor
public class ProductionCollaborator extends Collaborator {

    /**
     * Monetary value assigned per piece produced.
     */
    @Column
    private Double valuePerPiece;

    /**
     * Total number of pieces produced by the collaborator in the period.
     */
    @Column
    private Integer quantityProduced;

    /**
     * Constructs a {@code ProductionCollaborator} with all fields explicitly provided.
     *
     * @param id                 the unique identifier (use {@code null} for new records)
     * @param registrationNumber the unique registration number of the collaborator
     * @param name               the full name of the collaborator
     * @param valuePerPiece      the monetary value assigned per piece produced
     * @param quantityProduced   the total number of pieces produced in the period
     */
    public ProductionCollaborator(Long id, Integer registrationNumber, String name, Double valuePerPiece, Integer quantityProduced) {
        super(id, registrationNumber, name);
        this.valuePerPiece = valuePerPiece;
        this.quantityProduced = quantityProduced;
    }

    /**
     * Constructs a {@code ProductionCollaborator} with only production-related fields.
     *
     * <p>Used in scenarios where the base collaborator fields are set separately.</p>
     *
     * @param valuePerPiece    the monetary value assigned per piece produced
     * @param quantityProduced the total number of pieces produced in the period
     */
    public ProductionCollaborator(Double valuePerPiece, Integer quantityProduced) {
        this.valuePerPiece = valuePerPiece;
        this.quantityProduced = quantityProduced;
    }
}
