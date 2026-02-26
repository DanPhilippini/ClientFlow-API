package com.daniel.workboard.domain.mapper;

import com.daniel.workboard.domain.dto.project.ProjectRequestDTO;
import com.daniel.workboard.domain.dto.project.ProjectResponseDTO;
import com.daniel.workboard.domain.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public Project toEntity(ProjectRequestDTO dto) {
        return Project.builder()
                .title(dto.title())
                .description(dto.description())
                .build();
    }

    public ProjectResponseDTO toResponse(Project project) {
        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getCreatedAt()
        );
    }
}
