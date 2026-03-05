package com.daniel.workboard.service;

import com.daniel.workboard.domain.dto.user.UserRequestDTO;
import com.daniel.workboard.domain.dto.user.UserResponseDTO;
import com.daniel.workboard.domain.entity.User;
import com.daniel.workboard.domain.mapper.UserMapper;
import com.daniel.workboard.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldFindUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("Client A");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        User result = userService.findById(1L);

        assertThat(result.getName()).isEqualTo("Client A");
    }

    @Test
    void shouldFindAllUsers() {
        User user = new User();
        user.setId(1L);
        user.setName("Client A");

        when(userRepository.findAll())
                .thenReturn(List.of(user));

        List<UserResponseDTO> result = userService.findAll();

        assertThat(result).isNotNull();
    }



}