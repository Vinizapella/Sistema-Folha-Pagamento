package com.unisociesc.SistemaFolhaPagamento.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a commissioned collaborator in the payroll system.
 *
 * <p>A commissioned collaborator receives the base salary plus a commission
 * calculated from their total sales and commission percentage:</p>
 *
 * <pre>
 *   finalSalary = baseSalary + (totalSales * percentageCommission / 100)
 * </pre>
 *
 * <p>Stored in the {@code collaborator_table} with discriminator value {@code COMMISSIONED}.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see Collaborator
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("COMMISSIONED")
public class CommissionedContributor extends Collaborator {

    /**
     * Total sales amount achieved by the collaborator in the period.
     */
    @Column
    private Double totalSales;

    /**
     * Commission percentage applied over the total sales.
     * Represented as a value between 0 and 100 (e.g., {@code 10.0} means 10%).
     */
    @Column
    private Double percentageCommission;

    /**
     * Constructs a {@code CommissionedContributor} with all fields explicitly provided.
     *
     * @param id                   the unique identifier (use {@code null} for new records)
     * @param registrationNumber   the unique registration number of the collaborator
     * @param name                 the full name of the collaborator
     * @param totalSales           the total sales amount for the period
     * @param percentageCommission the commission percentage over total sales
     */
    public CommissionedContributor(Long id, Integer registrationNumber, String name, Double totalSales, Double percentageCommission) {
        super(id, registrationNumber, name);
        this.totalSales = totalSales;
        this.percentageCommission = percentageCommission;
    }
}