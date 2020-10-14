package com.lay.pulse.todo.repository;

import com.lay.pulse.todo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByTitleIgnoreCase(String title);

}
