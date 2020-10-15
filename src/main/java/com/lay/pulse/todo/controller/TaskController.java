package com.lay.pulse.todo.controller;


import com.lay.pulse.todo.model.Project;
import com.lay.pulse.todo.model.Task;
import com.lay.pulse.todo.model.dto.TaskDTO;
import com.lay.pulse.todo.service.TaskService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @ApiOperation(value = "Return a list of tasks saved")
    public ResponseEntity<List<Task>> findAll(){
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    @ApiOperation(value = "Save a task")
    public ResponseEntity<Task> create(@RequestBody @Valid TaskDTO task){
        Task taskSaved = taskService.create(task);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(taskSaved.getId()).toUri();
        return ResponseEntity.created(location).body(taskSaved);
    }

    @PutMapping
    @ApiOperation(value = "Update a task")
    public ResponseEntity<Task> update(@RequestBody @Valid TaskDTO task){
        Task taskUpdate = taskService.update(task);
        return ResponseEntity.ok(taskUpdate);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete a task")
    public ResponseEntity<Object> delete(Task task){
        taskService.delete(task);
        return ResponseEntity.ok().build();
    }
}
