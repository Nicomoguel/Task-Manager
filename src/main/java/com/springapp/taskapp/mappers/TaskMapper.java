package com.springapp.taskapp.mappers;

import com.springapp.taskapp.domain.dto.TaskDto;
import com.springapp.taskapp.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
