package com.lay.pulse.todo.repository;

import com.lay.pulse.todo.model.Project;
import com.lay.pulse.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitleIgnoreCase (String title);
    Optional<Task> findByTitleIgnoreCaseAndProjectId(String title, Long projectId);
    List<Task> findByTitleContainingIgnoreCase (String title);
}
