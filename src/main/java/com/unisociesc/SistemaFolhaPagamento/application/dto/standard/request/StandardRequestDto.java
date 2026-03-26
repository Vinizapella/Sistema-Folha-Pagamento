package com.unisociesc.SistemaFolhaPagamento.application.dto.standard.request;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StandardRequestDto(

        @NotNull(message = "Registration number is required")
        Integer registrationNumber,

        @NotBlank(message = "Name cannot be blank")
        String name

) implements CollaboratorRequestDto {
}
