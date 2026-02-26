package com.daniel.workboard.controller;

import com.daniel.workboard.domain.dto.AuthResponseDTO;
import com.daniel.workboard.domain.dto.LoginRequestDTO;
import com.daniel.workboard.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody LoginRequestDTO dto
    ) {
        return ResponseEntity.ok(service.login(dto));
    }
}
