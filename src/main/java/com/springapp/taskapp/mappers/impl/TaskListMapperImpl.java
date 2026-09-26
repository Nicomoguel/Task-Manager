package com.springapp.taskapp.mappers.impl;

import com.springapp.taskapp.domain.dto.TaskListDto;
import com.springapp.taskapp.domain.entities.Task;
import com.springapp.taskapp.domain.entities.TaskList;
import com.springapp.taskapp.domain.entities.TaskStatus;
import com.springapp.taskapp.mappers.TaskListMapper;
import com.springapp.taskapp.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper){
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto){
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks()).map(tasks -> tasks.stream().map(taskMapper::fromDto).toList()).orElse(null), // get all tasks in tasklist in form of a list
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList){
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0), // get the size of the task list with List::size
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks()).map(tasks -> tasks.stream().map(taskMapper::toDto).toList()).orElse(null)// get all tasks in tasklist in form of a list
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks){
        if(null == tasks){
            return null;
        }
        long closedTaskCount = tasks.stream().filter(task -> TaskStatus.CLOSED == task.getStatus()).count(); // filter all the tasks that are done and get the amount with .count()

        return (double) closedTaskCount / tasks.size();

    }

}
