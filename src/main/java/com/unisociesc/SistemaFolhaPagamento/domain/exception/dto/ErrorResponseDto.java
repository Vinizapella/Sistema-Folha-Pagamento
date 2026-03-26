package com.unisociesc.SistemaFolhaPagamento.domain.exception.dto;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message
) { }
