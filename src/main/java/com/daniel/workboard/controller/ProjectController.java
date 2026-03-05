package com.daniel.workboard.controller;

import com.daniel.workboard.config.ApiPaths;
import com.daniel.workboard.domain.dto.project.ProjectRequestDTO;
import com.daniel.workboard.domain.dto.project.ProjectResponseDTO;
import com.daniel.workboard.service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.API_V1 + "/projects")
@Tag(name = "Projects", description = "Endpoints for managing projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(
            @Valid @RequestBody ProjectRequestDTO request,
            Authentication authentication) {

        String email = authentication.getName();
        return ResponseEntity.ok(service.create(request, email));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> findAll(Pageable pageable, Authentication authentication) {

        String email = authentication.getName();
        return ResponseEntity.ok(service.findAllByUser(pageable, email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {

        String email = authentication.getName();
        service.delete(id, email);
        return ResponseEntity.noContent().build();
    }
}