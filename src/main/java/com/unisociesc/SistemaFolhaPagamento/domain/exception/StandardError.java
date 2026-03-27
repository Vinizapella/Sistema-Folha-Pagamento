package com.unisociesc.SistemaFolhaPagamento.domain.exception;

import java.time.Instant;

/**
 * Represents a standardized error response for the API.
 *
 * <p>This class is used as an alternative error body structure, providing
 * additional context such as the request path where the error occurred.
 * It complements {@code ErrorResponseDto} by including the {@code path} field.</p>
 *
 * @author Vinicius dos Santos Zapella
 * @version 1.0
 * @since 26/03/2026
 */
public class StandardError {

    /**
     * The exact moment the error occurred.
     */
    private Instant timestamp;

    /**
     * The HTTP status code associated with the error (e.g., {@code 404}, {@code 400}).
     */
    private Integer status;

    /**
     * A short description of the HTTP status (e.g., {@code "Not Found"}, {@code "Bad Request"}).
     */
    private String error;

    /**
     * A detailed message explaining the cause of the error.
     */
    private String message;

    /**
     * The request path where the error occurred (e.g., {@code "/api/collaborators/99"}).
     */
    private String path;

    /**
     * Default no-args constructor.
     */
    public StandardError() {}

    /**
     * Constructs a {@code StandardError} with all fields explicitly provided.
     *
     * @param timestamp the moment the error occurred
     * @param status    the HTTP status code
     * @param error     a short description of the HTTP status
     * @param message   a detailed message explaining the error
     * @param path      the request path where the error occurred
     */
    public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    /**
     * Returns the timestamp of the error.
     *
     * @return the error timestamp
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the error.
     *
     * @param timestamp the error timestamp
     */
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the HTTP status code.
     *
     * @return the HTTP status code
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code.
     *
     * @param status the HTTP status code
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Returns the short HTTP status description.
     *
     * @return the error description
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the short HTTP status description.
     *
     * @param error the error description
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Returns the detailed error message.
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the detailed error message.
     *
     * @param message the error message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the request path where the error occurred.
     *
     * @return the request path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the request path where the error occurred.
     *
     * @param path the request path
     */
    public void setPath(String path) {
        this.path = path;
    }
}