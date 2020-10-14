package com.lay.pulse.todo.controller;

import com.lay.pulse.todo.model.Project;
import com.lay.pulse.todo.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @ApiOperation(value = "Return a list of projects saved")
    public ResponseEntity<List<Project>> findAll(){
        List<Project> projects = projectService.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id){
        Project project = projectService.findById(id);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    @ApiOperation(value = "Save a project")
    public ResponseEntity<Project> create(@RequestBody Project project){
        Project projectSaved = projectService.create(project);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(projectSaved.getId()).toUri();
        return ResponseEntity.created(location).body(projectSaved);
    }

    @PutMapping
    @ApiOperation(value = "update a project")
    public ResponseEntity<Project> update(@RequestBody Project project){
        Project projectUpdate = projectService.update(project);
        return ResponseEntity.ok(projectUpdate);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete a project")
    public ResponseEntity<Object> delete(@RequestBody Project project){
        projectService.delete(project);
        return ResponseEntity.ok().build();
    }
}
