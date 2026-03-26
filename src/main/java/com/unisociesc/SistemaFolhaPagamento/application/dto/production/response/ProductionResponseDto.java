package com.unisociesc.SistemaFolhaPagamento.application.dto.production.response;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorResponseDto;

public record ProductionResponseDto(

        String name,

        Integer registrationNumber,

        Double baseSalary,

        Double extras,

        Double finalSalary

)implements CollaboratorResponseDto {
}
