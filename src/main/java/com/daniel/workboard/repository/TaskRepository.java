package com.daniel.workboard.repository;

import com.daniel.workboard.domain.entity.Task;
import com.daniel.workboard.domain.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId, Pageable pageable);

    List<Task> findByProjectIdAndStatus(
            Long projectId,
            TaskStatus status,
            Pageable pageable
    );
}
