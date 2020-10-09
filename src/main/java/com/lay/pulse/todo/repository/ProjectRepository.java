package com.lay.pulse.todo.repository;

import com.lay.pulse.todo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
