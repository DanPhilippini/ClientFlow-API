package com.daniel.workboard.domain.mapper;

import com.daniel.workboard.domain.dto.client.ClientRequestDTO;
import com.daniel.workboard.domain.dto.client.ClientResponseDTO;
import com.daniel.workboard.domain.entity.Client;

public class ClientMapper {

    public static Client toEntity(ClientRequestDTO dto) {
        return Client.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

    public static ClientResponseDTO toResponse(Client client) {
        return ClientResponseDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .build();
    }
}
