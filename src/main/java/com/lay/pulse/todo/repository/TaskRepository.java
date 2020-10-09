package com.lay.pulse.todo.repository;

import com.lay.pulse.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
