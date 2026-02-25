package com.daniel.workboard.service;

import com.daniel.workboard.domain.dto.client.ClientRequestDTO;
import com.daniel.workboard.domain.dto.client.ClientResponseDTO;
import com.daniel.workboard.domain.entity.Client;
import com.daniel.workboard.domain.entity.User;
import com.daniel.workboard.domain.mapper.ClientMapper;
import com.daniel.workboard.repository.ClientRepository;
import com.daniel.workboard.repository.UserRepository;
import com.daniel.workboard.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientResponseDTO create(ClientRequestDTO dto) {

        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();

        Client client = ClientMapper.toEntity(dto);
        client.setUser(user);

        Client saved = clientRepository.save(client);

        return ClientMapper.toResponse(saved);
    }


    public Client findById(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow();

        String email = SecurityUtils.getCurrentUserEmail();

        if (!client.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Access denied");
        }

        return client;
    }

    public List<ClientResponseDTO> findMyClients() {

        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();

        return clientRepository.findByUserId(user.getId())
                .stream()
                .map(ClientMapper::toResponse)
                .toList();
    }
}