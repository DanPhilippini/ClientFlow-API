package com.daniel.workboard.service;

import com.daniel.workboard.domain.dto.task.TaskRequestDTO;
import com.daniel.workboard.domain.dto.task.TaskResponseDTO;
import com.daniel.workboard.domain.entity.Project;
import com.daniel.workboard.domain.entity.Task;
import com.daniel.workboard.domain.entity.User;
import com.daniel.workboard.domain.mapper.TaskMapper;
import com.daniel.workboard.repository.ProjectRepository;
import com.daniel.workboard.repository.TaskRepository;
import com.daniel.workboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;

    public TaskResponseDTO create(Long projectId,
                                  TaskRequestDTO request,
                                  String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You don't own this project");
        }

        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setProject(project);

        taskRepository.save(task);

        return mapper.toResponse(task);
    }

    public List<TaskResponseDTO> findByProject(Long projectId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You don't own this project");
        }

        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
