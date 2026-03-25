package com.unisociesc.SistemaFolhaPagamento.dto.commissioned.response;

import com.unisociesc.SistemaFolhaPagamento.dto.CollaboratorResponseDto;

public record CommissionedResponseDto(

        String name,

        Integer registrationNumber,

        Double baseSalary,

        Double extras,

        Double finalSalary

)implements CollaboratorResponseDto {
}
