package com.daniel.workboard.service;

import com.daniel.workboard.domain.dto.task.TaskRequestDTO;
import com.daniel.workboard.domain.dto.task.TaskResponseDTO;
import com.daniel.workboard.domain.dto.task.UpdateTaskStatusRequestDTO;
import com.daniel.workboard.domain.entity.Project;
import com.daniel.workboard.domain.entity.Task;
import com.daniel.workboard.domain.entity.User;
import com.daniel.workboard.domain.mapper.TaskMapper;
import com.daniel.workboard.domain.model.TaskStatus;
import com.daniel.workboard.repository.ProjectRepository;
import com.daniel.workboard.repository.TaskRepository;
import com.daniel.workboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private static final Logger log = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;

    public TaskResponseDTO create(Long projectId,
                                  TaskRequestDTO request,
                                  String userEmail) {

        log.info("Creating task '{}' on project {}", request.title(), projectId);

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

    public List<TaskResponseDTO> findByProject(Long projectId, TaskStatus status, Pageable pageable, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You don't own this project");
        }

        List<Task> tasks;

        if (status != null) {
            tasks = taskRepository.findByProjectIdAndStatus(projectId, status, pageable);
        } else {
            tasks = taskRepository.findByProjectId(projectId, pageable);
        }

        return tasks
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public TaskResponseDTO updateStatus(Long projectId,
                                     Long taskId,
                                     UpdateTaskStatusRequestDTO request,
                                     String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow();

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You don't own this project");
        }

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getProject().getId().equals(projectId)) {
            throw new RuntimeException("Task don't bellow to this project");
        }

        task.setStatus(request.status());

        taskRepository.save(task);

        return mapper.toResponse(task);
    }
}
