package com.daniel.workboard.service;

import com.daniel.workboard.domain.dto.AuthResponseDTO;
import com.daniel.workboard.domain.dto.LoginRequestDTO;
import com.daniel.workboard.domain.entity.User;
import com.daniel.workboard.exception.BusinessException;
import com.daniel.workboard.repository.UserRepository;
import com.daniel.workboard.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDTO login(LoginRequestDTO dto) {
        User user = repository.findByEmail(dto.email())
                .orElseThrow(() -> new BusinessException("Invalid credentials"));

        log.warn("Invalid credentials - user {}", dto.email());

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new BusinessException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponseDTO(token);
    }
}