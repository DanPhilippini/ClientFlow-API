package com.daniel.workboard.domain.dto.task;

import com.daniel.workboard.domain.model.TaskStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskStatusRequestDTO(
        @NotNull(message = "Status is required")
        TaskStatus status
) { }
