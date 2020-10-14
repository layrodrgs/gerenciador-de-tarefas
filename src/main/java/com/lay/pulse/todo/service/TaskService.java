package com.lay.pulse.todo.service;

import com.lay.pulse.todo.model.Project;
import com.lay.pulse.todo.model.Task;
import com.lay.pulse.todo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task create(Task task){
        return taskRepository.save(task);
    }

    public Task update(Task task){
        return taskRepository.save(task);
    }

    public void delete(Task task){
        taskRepository.delete(task);
    }
}
