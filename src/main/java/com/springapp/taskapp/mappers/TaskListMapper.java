package com.springapp.taskapp.mappers;

import com.springapp.taskapp.domain.dto.TaskListDto;
import com.springapp.taskapp.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);

}
