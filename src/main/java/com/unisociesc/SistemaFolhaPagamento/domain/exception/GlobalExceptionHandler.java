package com.unisociesc.SistemaFolhaPagamento.domain.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

/**
 * Global exception handler for the payroll system API.
 *
 * <p>Centralizes exception handling across all controllers using
 * {@link RestControllerAdvice}, ensuring consistent and structured
 * error responses for all failure scenarios.</p>
 *
 * <p>All responses follow the {@link StandardError} structure, including
 * the timestamp, HTTP status code, short error description, detailed message,
 * and the request path where the error occurred.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 * @see StandardError
 * @see ResourceNotFoundException
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link ResourceNotFoundException} thrown when a collaborator is not found.
     *
     * <p>Returns an HTTP {@code 404 Not Found} response with the exception message
     * as the error detail.</p>
     *
     * @param e       the exception containing the not-found message
     * @param request the HTTP request that triggered the exception
     * @return a {@link ResponseEntity} with status {@code 404} and a {@link StandardError} body
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(
            ResourceNotFoundException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Resource not found",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Handles {@link HttpMessageNotReadableException} thrown when the request body
     * contains malformed JSON or an incorrect data type.
     *
     * <p>Returns an HTTP {@code 400 Bad Request} response with a fixed descriptive message.</p>
     *
     * @param e       the exception indicating the unreadable message
     * @param request the HTTP request that triggered the exception
     * @return a {@link ResponseEntity} with status {@code 400} and a {@link StandardError} body
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> badRequest(
            HttpMessageNotReadableException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Bad request",
                "Malformed JSON or incorrect data type.",
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Handles {@link DataIntegrityViolationException} thrown when a database constraint
     * is violated, such as a duplicate unique registration number.
     *
     * <p>Returns an HTTP {@code 409 Conflict} response with a fixed descriptive message.</p>
     *
     * @param e       the exception indicating the data integrity violation
     * @param request the HTTP request that triggered the exception
     * @return a {@link ResponseEntity} with status {@code 409} and a {@link StandardError} body
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> databaseConflict(
            DataIntegrityViolationException e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Data conflict",
                "You are trying to save data that already exists and must be unique.",
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

    /**
     * Handles any unhandled {@link Exception} not caught by the more specific handlers above.
     *
     * <p>Acts as a catch-all fallback, returning an HTTP {@code 500 Internal Server Error}
     * response to avoid exposing internal details to the client.</p>
     *
     * @param e       the unexpected exception
     * @param request the HTTP request that triggered the exception
     * @return a {@link ResponseEntity} with status {@code 500} and a {@link StandardError} body
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> genericException(
            Exception e,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Internal server error",
                "An unexpected error occurred. Please contact support.",
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}