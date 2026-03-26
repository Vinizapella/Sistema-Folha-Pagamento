package com.unisociesc.SistemaFolhaPagamento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unisociesc.SistemaFolhaPagamento.controller.CollaboratorController;
import com.unisociesc.SistemaFolhaPagamento.dto.standard.request.StandardRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.standard.response.StandardResponseDto;
import com.unisociesc.SistemaFolhaPagamento.dto.commissioned.request.CommissionedRequestDto;
import com.unisociesc.SistemaFolhaPagamento.dto.commissioned.response.CommissionedResponseDto;
import com.unisociesc.SistemaFolhaPagamento.exception.ResourceNotFoundException;
import com.unisociesc.SistemaFolhaPagamento.service.CollaboratorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CollaboratorController.class)
class CollaboratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CollaboratorService service;

    @Test
    @DisplayName("POST /api/collaborators - Should return 201 Created with the created collaborator")
    void shouldRegisterCollaboratorAndReturn201Created() throws Exception {
        StandardRequestDto request = new StandardRequestDto(
                1,
                "João"
        );
        StandardResponseDto response = new StandardResponseDto(
                "João",
                1,
                3000.0,
                0.0,
                3000.0
        );

        when(service.register(any())).thenReturn(response);

        mockMvc.perform(post("/api/collaborators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("João"))
                .andExpect(jsonPath("$.finalSalary").value(3000.0));
    }

    @Test
    @DisplayName("GET /api/collaborators - Should return 200 OK with a list of collaborators")
    void shouldReturnListAndStatus200OkWhenCollaboratorsExist() throws Exception {
        List<StandardResponseDto> lista = List.of(
                new StandardResponseDto(
                        "João",
                        1,
                        3000.0,
                        0.0,
                        3000.0
                ),
                new StandardResponseDto(
                        "Victoria",
                        2,
                        3000.0,
                        0.0,
                        3000.0
                )
        );

        when(service.listAll()).thenReturn((List) lista);

        mockMvc.perform(get("/api/collaborators"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("João"))
                .andExpect(jsonPath("$[1].name").value("Victoria"));
    }

    @Test
    @DisplayName("GET /api/collaborators - Should return 200 OK with an empty list")
    void shouldReturn200OkWithEmptyListWhenNoCollaboratorsExist() throws Exception {
        when(service.listAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/collaborators"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("PUT /api/collaborators/{id} - Should return 200 OK with updated collaborator")
    void shouldUpdateCollaboratorAndReturn200OkWhenIdExists() throws Exception {
        CommissionedRequestDto request = new CommissionedRequestDto(
                2,
                "Victoria",
                5000.0,
                10.0
        );
        CommissionedResponseDto response = new CommissionedResponseDto(
                "Victoria",
                2,
                3000.0,
                500.0,
                3500.0
        );

        when(service.update(any(), eq(1L))).thenReturn(response);

        mockMvc.perform(put("/api/collaborators/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Victoria"))
                .andExpect(jsonPath("$.finalSalary").value(3500.0));
    }

    @Test
    @DisplayName("PUT /api/collaborators/{id} - Should return 404 Not Found when ID does not exist")
    void shouldReturn404NotFoundWhenUpdatingNonExistentCollaborator() throws Exception {
        CommissionedRequestDto request = new CommissionedRequestDto(
                2,
                "Victoria",
                5000.0,
                10.0
        );

        when(service.update(any(), eq(99L)))
                .thenThrow(new ResourceNotFoundException("No collaborator found with ID: 99"));

        mockMvc.perform(put("/api/collaborators/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/collaborators/{id} - Should return 204 No Content when successfully deleted")
    void shouldReturn204NoContentWhenSuccessfullyDeleted() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/api/collaborators/1"))
                .andExpect(status().isNoContent());

        verify(service).delete(1L);
    }

    @Test
    @DisplayName("DELETE /api/collaborators/{id} - Should return 404 Not Found when ID does not exist")
    void shouldReturn404NotFoundWhenDeletingNonExistentCollaborator() throws Exception {
        doThrow(new ResourceNotFoundException("No collaborator found to delete with ID: 99"))
                .when(service).delete(99L);

        mockMvc.perform(delete("/api/collaborators/99"))
                .andExpect(status().isNotFound());
    }
}