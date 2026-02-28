package com.daniel.workboard.controller;

import com.daniel.workboard.common.ApiResponse;
import com.daniel.workboard.config.ApiPaths;
import com.daniel.workboard.domain.dto.task.TaskRequestDTO;
import com.daniel.workboard.domain.dto.task.TaskResponseDTO;
import com.daniel.workboard.domain.dto.task.UpdateTaskStatusRequestDTO;
import com.daniel.workboard.domain.model.TaskStatus;
import com.daniel.workboard.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.API_V1 + "/projects/{projectId}/tasks")
@Tag(name = "Tasks", description = "Endpoints for managing tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponseDTO>> create(
            @PathVariable Long projectId,
            @Valid @RequestBody TaskRequestDTO request,
            Authentication authentication) {

        TaskResponseDTO taskResponse = service.create(projectId, request, authentication.getName());
        return ResponseEntity.ok(
                ApiResponse.success("Task created successful", taskResponse)
        );
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAll(
            @PathVariable Long projectId,
            @RequestParam(required = false) TaskStatus status,
            Pageable pageable,
            Authentication authentication) {

        return ResponseEntity.ok(
                service.findByProject(projectId, status, pageable, authentication.getName())
        );
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<ApiResponse<TaskResponseDTO>> updateStatus(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @Valid @RequestBody UpdateTaskStatusRequestDTO request,
            Authentication authentication) {

        TaskResponseDTO taskResponse = service.updateStatus(projectId, taskId, request, authentication.getName());
        return ResponseEntity.ok(
                ApiResponse.success("Task updated successful", taskResponse)
        );
    }
}
