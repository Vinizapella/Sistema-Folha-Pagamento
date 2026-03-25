package com.unisociesc.SistemaFolhaPagamento.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.unisociesc.SistemaFolhaPagamento.dto.commissioned.response.CommissionedResponseDto;
import com.unisociesc.SistemaFolhaPagamento.dto.production.response.ProductionResponseDto;
import com.unisociesc.SistemaFolhaPagamento.dto.standard.response.StandardResponseDto;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "bond_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StandardResponseDto.class, name = "STANDARD"),
        @JsonSubTypes.Type(value = CommissionedResponseDto.class, name = "COMMISSIONED"),
        @JsonSubTypes.Type(value = ProductionResponseDto.class, name = "PRODUCTION")
})
public interface CollaboratorResponseDto {

    Integer registrationNumber();

    String name();

    Double baseSalary();

    Double finalSalary();

}
