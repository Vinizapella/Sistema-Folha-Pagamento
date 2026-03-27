package com.unisociesc.SistemaFolhaPagamento.domain.exception.dto;

import java.time.LocalDateTime;

/**
 * DTO representing the standardized error response body returned by the API.
 *
 * <p>Used by the {@code GlobalExceptionHandler} to provide a consistent error
 * structure across all exception responses, making it easier for clients to
 * handle and display error information.</p>
 *
 * @param timestamp the date and time when the error occurred
 * @param status    the HTTP status code associated with the error (e.g., {@code 404}, {@code 400})
 * @param error     a short description of the HTTP status (e.g., {@code "Not Found"}, {@code "Bad Request"})
 * @param message   a detailed message explaining the cause of the error
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 */
public record ErrorResponseDto(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message
) { }