package com.unisociesc.SistemaFolhaPagamento.infra.controller;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorRequestDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorResponseDto;
import com.unisociesc.SistemaFolhaPagamento.domain.contract.CollaboratorUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborators")
@CrossOrigin(origins = "*")
public class CollaboratorController {

    private final CollaboratorUseCase service;

    public CollaboratorController(
            CollaboratorUseCase service
    ) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CollaboratorResponseDto> register(
            @RequestBody @Valid CollaboratorRequestDto requestDto
    ) {
        CollaboratorResponseDto response = service.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CollaboratorResponseDto>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollaboratorResponseDto> update(
            @RequestBody @Valid CollaboratorRequestDto requestDto,
            @PathVariable Long id
    ) {
        CollaboratorResponseDto response = service.update(requestDto, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}