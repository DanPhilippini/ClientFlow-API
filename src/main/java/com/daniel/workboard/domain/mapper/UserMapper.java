package com.daniel.workboard.domain.mapper;

import com.daniel.workboard.domain.dto.user.UserRequestDTO;
import com.daniel.workboard.domain.dto.user.UserResponseDTO;
import com.daniel.workboard.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO dto) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    public UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
