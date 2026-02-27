package com.daniel.workboard.domain.dto.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        com.daniel.workboard.domain.model.TaskStatus status,
        LocalDate dueDate,
        LocalDateTime createdAt
) {}
