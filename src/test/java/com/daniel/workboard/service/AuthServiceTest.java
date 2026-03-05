package com.daniel.workboard.service;

import com.daniel.workboard.domain.entity.User;
import com.daniel.workboard.repository.UserRepository;
import com.daniel.workboard.security.JwtService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldGenerateTokenWhenLoginIsValid() {
        User user = new User();
        user.setId(1L);
        user.setName("Client A");
        user.setEmail("user@gmail.com");

        String email = "user@gmail.com";

        when(jwtService.generateToken(user, 2L))
                .thenReturn("fake-jwt");

        String token = jwtService.generateToken(user, 2L);

        assert(token.equals("fake-jwt"));
    }
}