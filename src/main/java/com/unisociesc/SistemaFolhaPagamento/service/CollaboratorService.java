package com.unisociesc.SistemaFolhaPagamento.service;

import com.unisociesc.SistemaFolhaPagamento.dto.CollaboratorRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.CollaboratorResponseDto;

import java.util.List;

public interface CollaboratorService {

    CollaboratorResponseDto register(
            CollaboratorRequestDto collaboratorRequestDto
    );

    List<CollaboratorResponseDto> listAll();

    CollaboratorResponseDto update(
            CollaboratorRequestDto collaboratorRequestDto,
            Long id
    );

    void delete(
            Long id
    );

}
