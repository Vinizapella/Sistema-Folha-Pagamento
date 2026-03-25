package com.unisociesc.SistemaFolhaPagamento.dto.production.response;

import com.unisociesc.SistemaFolhaPagamento.dto.CollaboratorResponseDto;

public record ProductionResponseDto(

        String name,

        Integer registrationNumber,

        Double baseSalary,

        Double productivity,

        Double finalSalary

)implements CollaboratorResponseDto {
}
