package com.unisociesc.SistemaFolhaPagamento.dto.standard.response;

import com.unisociesc.SistemaFolhaPagamento.dto.CollaboratorResponseDto;

public record StandardResponseDto(

        String name,

        Integer registrationNumber,

        Double baseSalary,

        Double extras,

        Double finalSalary

)implements CollaboratorResponseDto {
}
