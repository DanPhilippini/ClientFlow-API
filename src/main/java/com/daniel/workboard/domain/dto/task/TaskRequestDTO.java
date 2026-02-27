package com.daniel.workboard.domain.dto.task;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TaskRequestDTO(
        @NotBlank(message = "Title is required")
        String title,
        String description,
        LocalDate dueDate
) {}
