package com.daniel.workboard.domain.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectRequestDTO (
    @NotBlank(message = "Title is required")
    @Size(min = 3, message = "Title must have min 3 characters")
    String title,
    @NotBlank(message = "Description is required")
    String description
) {}
