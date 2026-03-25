package com.unisociesc.SistemaFolhaPagamento.dto.commissioned.request;

import com.unisociesc.SistemaFolhaPagamento.dto.CollaboratorRequestDto;
import jakarta.validation.constraints.*;

public record CommissionedRequestDto(

        @NotNull(message = "Registration number is required")
        Integer registrationNumber,

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotNull(message = "Total sales must be provided")
        @Positive(message = "Total sales must be a positive value")
        Double totalSales,

        @NotNull(message = "Commission percentage is required")
        @DecimalMin(value = "0.0", message = "Commission cannot be negative")
        @DecimalMax(value = "100.0", message = "Commission cannot exceed 100%")
        Double percentageCommission

) implements CollaboratorRequestDto {
}
