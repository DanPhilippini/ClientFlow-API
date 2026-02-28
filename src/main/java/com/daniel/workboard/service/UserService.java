package com.daniel.workboard.service;

import com.daniel.workboard.domain.dto.UserRequestDTO;
import com.daniel.workboard.domain.dto.UserResponseDTO;
import com.daniel.workboard.domain.entity.User;
import com.daniel.workboard.domain.mapper.UserMapper;
import com.daniel.workboard.exception.BusinessException;
import com.daniel.workboard.domain.model.Role;
import com.daniel.workboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserResponseDTO create(UserRequestDTO dto) {

        repository.findByEmail(dto.email())
                .ifPresent(u -> {
                    throw new BusinessException("Email already exists");
                });

        User user = mapper.toEntity(dto);

        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(Role.USER);

        User saved = repository.save(user);

        return mapper.toResponse(saved);
    }

    public List<UserResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}