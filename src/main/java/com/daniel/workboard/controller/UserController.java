package com.daniel.workboard.controller;

import com.daniel.workboard.domain.dto.UserRequestDTO;
import com.daniel.workboard.domain.dto.UserResponseDTO;
import com.daniel.workboard.domain.entity.User;
import com.daniel.workboard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(
            @Valid @RequestBody UserRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> list() {
        return ResponseEntity.ok(service.findAll());
    }
}