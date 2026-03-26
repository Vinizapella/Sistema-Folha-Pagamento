package com.unisociesc.SistemaFolhaPagamento.application.dto.standard.response;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorResponseDto;

public record StandardResponseDto(

        String name,

        Integer registrationNumber,

        Double baseSalary,

        Double extras,

        Double finalSalary

)implements CollaboratorResponseDto {
}
