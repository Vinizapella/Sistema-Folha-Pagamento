package com.unisociesc.SistemaFolhaPagamento.infra.controller;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorRequestDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorResponseDto;
import com.unisociesc.SistemaFolhaPagamento.domain.contract.CollaboratorUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborators")
@CrossOrigin(origins = "*")
@Tag(
        name = "Collaborators",
        description = "Payroll employee management"
)
public class CollaboratorController {

    private final CollaboratorUseCase service;

    public CollaboratorController(
            CollaboratorUseCase service
    ) {
        this.service = service;
    }

    @Operation(
            summary = "Register collaborator",
            description = "Register a new employee. The type is defined by the field `bond_type`: STANDARD, COMMISSIONED or PRODUCTION"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Employee successfully registered",
                    content = @Content(schema = @Schema(implementation = CollaboratorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data in the request",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<CollaboratorResponseDto> register(
            @RequestBody @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Employee data to be registered. Use `bond_type` to define the type",
                    required = true
            )
            CollaboratorRequestDto requestDto

    ) {
        CollaboratorResponseDto response = service.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "List all collaborators",
            description = "Returns a list of all employees with their respective calculated salaries"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List returned successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CollaboratorResponseDto.class)))
            )
    })
    @GetMapping
    public ResponseEntity<List<CollaboratorResponseDto>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @Operation(
            summary = "Update collaborator",
            description = "Updates the data of an existing employee by ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Collaborator successfully updated",
                    content = @Content(schema = @Schema(implementation = CollaboratorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid data in the request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Contributor not found",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<CollaboratorResponseDto> update(
            @RequestBody @Valid CollaboratorRequestDto requestDto,
            @Parameter(description = "Employee ID to be updated",
                    required = true, example = "1")
            @PathVariable Long id
    ) {
        CollaboratorResponseDto response = service.update(requestDto, id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Delete collaborator",
            description = "Remove an employee from the payroll by ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Contributor successfully removed",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Contributor not found",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Employee ID to be deleted",
                    required = true, example = "1")
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}