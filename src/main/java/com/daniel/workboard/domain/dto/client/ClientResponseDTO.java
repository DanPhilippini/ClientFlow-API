package com.daniel.workboard.domain.dto.client;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientResponseDTO {

    private Long id;
    private String name;
    private String email;
}