package com.lay.pulse.todo.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TaskDTO {

    private Long id;
    @NotBlank
    private String title;
    @NotNull
    private Long projectId;
}
