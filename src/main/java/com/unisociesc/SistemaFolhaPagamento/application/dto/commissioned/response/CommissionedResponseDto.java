package com.unisociesc.SistemaFolhaPagamento.application.dto.commissioned.response;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorResponseDto;

public record CommissionedResponseDto(

        String name,

        Integer registrationNumber,

        Double baseSalary,

        Double extras,

        Double finalSalary

)implements CollaboratorResponseDto {
}
