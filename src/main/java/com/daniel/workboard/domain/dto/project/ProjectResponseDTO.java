package com.daniel.workboard.domain.dto.project;

import java.time.LocalDateTime;

public record ProjectResponseDTO (
    Long id,
    String title,
    String description,
    LocalDateTime createdAt
){}
