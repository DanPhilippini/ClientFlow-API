package com.daniel.workboard.service;

import com.daniel.workboard.domain.entity.Project;
import com.daniel.workboard.domain.entity.User;
import com.daniel.workboard.repository.ProjectRepository;
import com.daniel.workboard.repository.UserRepository;
import com.daniel.workboard.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Project create(Project project) {

        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();

        project.setUser(user);

        return projectRepository.save(project);
    }

    public List<Project> findMyProjects() {

        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();

        return projectRepository.findByUserId(user.getId());
    }
}