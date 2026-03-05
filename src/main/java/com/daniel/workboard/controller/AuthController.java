package com.daniel.workboard.controller;

import com.daniel.workboard.config.ApiPaths;
import com.daniel.workboard.domain.dto.AuthResponseDTO;
import com.daniel.workboard.domain.dto.LoginRequestDTO;
import com.daniel.workboard.domain.dto.RefreshTokenRequestDTO;
import com.daniel.workboard.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.API_V1 + "/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Endpoints for authentication")
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody LoginRequestDTO dto
    ) {
        return ResponseEntity.ok(service.login(dto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refresh(
            @RequestBody RefreshTokenRequestDTO dto
    ) {
        return ResponseEntity.ok(service.refreshToken(dto));
    }
}
