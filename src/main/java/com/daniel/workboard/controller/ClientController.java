package com.daniel.workboard.controller;

import com.daniel.workboard.domain.dto.client.ClientRequestDTO;
import com.daniel.workboard.domain.dto.client.ClientResponseDTO;
import com.daniel.workboard.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(
            @Valid @RequestBody ClientRequestDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(dto));
    }
}
