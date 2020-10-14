package com.lay.pulse.todo.repository;

import com.lay.pulse.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitleIgnoreCase (String title);
    List<Task> findByTitleContainingIgnoreCase (String title);
}
