package com.unisociesc.SistemaFolhaPagamento.mapper;

import com.unisociesc.SistemaFolhaPagamento.dto.CollaboratorRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.CollaboratorResponseDto;
import com.unisociesc.SistemaFolhaPagamento.dto.commissioned.request.CommissionedRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.commissioned.response.CommissionedResponseDto;
import com.unisociesc.SistemaFolhaPagamento.dto.production.request.ProductionRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.production.response.ProductionResponseDto;
import com.unisociesc.SistemaFolhaPagamento.dto.standard.request.StandardRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.standard.response.StandardResponseDto;
import com.unisociesc.SistemaFolhaPagamento.model.Collaborator;
import com.unisociesc.SistemaFolhaPagamento.model.CommissionedContributor;
import com.unisociesc.SistemaFolhaPagamento.model.ProductionCollaborator;
import com.unisociesc.SistemaFolhaPagamento.model.StandardContributor;
import org.springframework.stereotype.Component;

@Component
public class CollaboratorMapper {

    public Collaborator toEntity(
            CollaboratorRequestDto collaboratorRequestDto
    ){
        if (collaboratorRequestDto instanceof StandardRequestDto standardRequestDto){
            return new StandardContributor(
                    null,
                    standardRequestDto.registrationNumber(),
                    standardRequestDto.name()
            );
        }

        if (collaboratorRequestDto instanceof CommissionedRequestDto commissionedRequestDto){
            return new CommissionedContributor(
                    null,
                    commissionedRequestDto.registrationNumber(),
                    commissionedRequestDto.name(),
                    commissionedRequestDto.totalSales(),
                    commissionedRequestDto.percentageCommission()
            );
        }

        if (collaboratorRequestDto instanceof ProductionRequestDto productionRequestDto){
            return new ProductionCollaborator(
                    null,
                    productionRequestDto.registrationNumber(),
                    productionRequestDto.name(),
                    productionRequestDto.valuePerPiece(),
                    productionRequestDto.quantityProduced()
            );
        }

        throw new IllegalArgumentException("Invalid employee type");

    }

    public CollaboratorResponseDto toResponse(
            Collaborator collaborator,
            Double baseSalary,
            Double extra,
            Double finalSalary
    ) {
        if (collaborator instanceof StandardContributor) {
            return new StandardResponseDto(
                    collaborator.getName(),
                    collaborator.getRegistrationNumber(),
                    baseSalary,
                    0.0,
                    finalSalary
            );
        }
        if (collaborator instanceof CommissionedContributor) {
            return new CommissionedResponseDto(
                    collaborator.getName(),
                    collaborator.getRegistrationNumber(),
                    baseSalary,
                    extra,
                    finalSalary
            );
        }
        if (collaborator instanceof ProductionCollaborator) {
            return new ProductionResponseDto(
                    collaborator.getName(),
                    collaborator.getRegistrationNumber(),
                    baseSalary,
                    extra,
                    finalSalary
            );
        }
        throw new IllegalArgumentException("Unknown entity type");
    }
}
