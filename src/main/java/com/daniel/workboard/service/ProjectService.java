package com.daniel.workboard.service;

import com.daniel.workboard.domain.dto.project.ProjectRequestDTO;
import com.daniel.workboard.domain.dto.project.ProjectResponseDTO;
import com.daniel.workboard.domain.entity.Project;
import com.daniel.workboard.domain.entity.User;
import com.daniel.workboard.domain.mapper.ProjectMapper;
import com.daniel.workboard.repository.ProjectRepository;
import com.daniel.workboard.repository.UserRepository;
import com.daniel.workboard.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper mapper;

    public ProjectResponseDTO create(ProjectRequestDTO request, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = new Project();
        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setUser(user);

        projectRepository.save(project);

        return mapper.toResponse(project);
    }

    public List<ProjectResponseDTO> findAllByUser(Pageable pageable, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();

        return projectRepository.findByUserId(user.getId(), pageable)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProjectResponseDTO findById(Long id) {
        return projectRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void delete(Long id, String userEmail) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("You can't delete this project");
        }

        projectRepository.deleteById(id);
    }
}