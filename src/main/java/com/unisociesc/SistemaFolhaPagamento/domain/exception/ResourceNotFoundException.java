package com.unisociesc.SistemaFolhaPagamento.domain.exception;

/**
 * Exception thrown when a requested resource is not found in the system.
 *
 * <p>Typically used when a collaborator lookup by ID returns no results,
 * resulting in an HTTP {@code 404 Not Found} response handled by the
 * {@code GlobalExceptionHandler}.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a {@code ResourceNotFoundException} with the specified detail message.
     *
     * @param message a message describing which resource was not found
     *                (e.g., {@code "No collaborator found with ID: 99"})
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}