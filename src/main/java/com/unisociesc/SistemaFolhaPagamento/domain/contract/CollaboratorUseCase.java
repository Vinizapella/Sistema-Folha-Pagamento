package com.unisociesc.SistemaFolhaPagamento.domain.contract;

import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorRequestDto;
import com.unisociesc.SistemaFolhaPagamento.application.dto.CollaboratorResponseDto;

import java.util.List;

public interface CollaboratorUseCase {

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
