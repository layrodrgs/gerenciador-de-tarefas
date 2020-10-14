package com.lay.pulse.todo.service;

import com.lay.pulse.todo.exception.DuplicatedEntityException;
import com.lay.pulse.todo.exception.EntityNotFoundException;
import com.lay.pulse.todo.model.Project;
import com.lay.pulse.todo.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public Project findById(Long id){
       return projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Project create(Project project){
        Optional<Project> exist = projectRepository.findByTitleIgnoreCase(project.getTitle());
        if(project.getId() != null || exist.isPresent()){
            throw new DuplicatedEntityException();
        }
        return projectRepository.save(project);
    }

    public Project update(Project project){
        if(project.getId() == null || !projectRepository.findById(project.getId()).isPresent()){
            throw new EntityNotFoundException();
        }
        Optional<Project> exist = projectRepository.findByTitleIgnoreCase(project.getTitle());
        if(exist.isPresent() && !exist.get().getId().equals(project.getId())){
            throw new DuplicatedEntityException();
        }
        return projectRepository.save(project);
    }

    public void delete(Project project){
        if(project.getId() == null || !projectRepository.findById(project.getId()).isPresent()){
            throw new EntityNotFoundException();
        }
        projectRepository.delete(project);
    }
}
