package com.daniel.workboard.service;

import com.daniel.workboard.domain.dto.project.ProjectRequestDTO;
import com.daniel.workboard.domain.dto.project.ProjectResponseDTO;
import com.daniel.workboard.domain.entity.Project;
import com.daniel.workboard.domain.entity.User;
import com.daniel.workboard.domain.mapper.ProjectMapper;
import com.daniel.workboard.repository.ProjectRepository;
import com.daniel.workboard.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {


    @Mock
    private ProjectMapper mapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void shouldFindProjectById() {
        User user = new User();
        user.setId(1L);
        user.setName("Client A");
        user.setEmail("user@gmail.com");

        Project project = new Project();
        project.setId(2L);
        project.setTitle("Project A");
        project.setUser(user);

        ProjectResponseDTO response =
                new ProjectResponseDTO(2L, "Project A", "Description", LocalDateTime.now());

        when(projectRepository.findById(2L))
                .thenReturn(Optional.of(project));

        when(mapper.toResponse(project))
                .thenReturn(response);

        ProjectResponseDTO result = projectService.findById(2L);

        assertThat(result.title()).isEqualTo("Project A");
    }

    @Test
    void shouldFindAllProjects() {
        User user = new User();
        user.setId(1L);
        user.setName("Client A");
        user.setEmail("user@gmail.com");

        Project project = new Project();
        project.setId(1L);
        project.setTitle("Project A");
        project.setUser(user);


        when(userRepository.findByEmail("user@gmail.com"))
                .thenReturn(Optional.of(user));

        when(projectRepository.findByUserId(1L, Pageable.ofSize(2)))
                .thenReturn(List.of(project));

        List<ProjectResponseDTO> result = projectService.findAllByUser(Pageable.ofSize(2), "user@gmail.com");

        assertThat(result).isNotNull();
    }
}