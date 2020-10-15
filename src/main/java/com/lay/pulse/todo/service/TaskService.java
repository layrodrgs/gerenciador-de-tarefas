package com.lay.pulse.todo.service;

import com.lay.pulse.todo.exception.DuplicatedEntityException;
import com.lay.pulse.todo.exception.EntityNotFoundException;
import com.lay.pulse.todo.model.Project;
import com.lay.pulse.todo.model.Task;
import com.lay.pulse.todo.model.dto.TaskDTO;
import com.lay.pulse.todo.repository.ProjectRepository;
import com.lay.pulse.todo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task create(TaskDTO task){
        Project existProject = projectRepository.findById(task.getProjectId()).orElseThrow(()-> new EntityNotFoundException("Project not found."));
        Optional<Task> exist = taskRepository.findByTitleIgnoreCaseAndProjectId(task.getTitle(), task.getProjectId());
        if(task.getId() != null || exist.isPresent()){
            throw new DuplicatedEntityException("Task already exist.");
        }
        Task newTask = Task.builder().title(task.getTitle()).project(existProject).build();
        return taskRepository.save(newTask);
    }

    public Task update(TaskDTO task){
        Task newTask = Task.builder().title(task.getTitle()).project(Project.builder().id(task.getProjectId()).build()).build();
        return taskRepository.save(newTask);
    }

    public void delete(Task task){
        taskRepository.delete(task);
    }
}
