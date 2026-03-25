package com.unisociesc.SistemaFolhaPagamento.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.unisociesc.SistemaFolhaPagamento.dto.standard.request.StandardRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.commissioned.request.CommissionedRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.production.request.ProductionRequestDto;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "bond_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StandardRequestDto.class, name = "STANDARD"),
        @JsonSubTypes.Type(value = CommissionedRequestDto.class, name = "COMMISSIONED"),
        @JsonSubTypes.Type(value = ProductionRequestDto.class, name = "PRODUCTION")
})
public interface CollaboratorRequestDto {

    Integer registrationNumber();

    String name();

}
