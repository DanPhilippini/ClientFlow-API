package com.daniel.workboard.repository;

import com.daniel.workboard.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByClientId(Long clientId);
}
