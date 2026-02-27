package com.daniel.workboard.domain.mapper;

import com.daniel.workboard.domain.dto.task.TaskRequestDTO;
import com.daniel.workboard.domain.dto.task.TaskResponseDTO;
import com.daniel.workboard.domain.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task toEntity(TaskRequestDTO dto) {
        return Task.builder()
                .title(dto.title())
                .description(dto.description())
                .build();
    }

    public TaskResponseDTO toResponse(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                task.getCreatedAt()
        );
    }
}
