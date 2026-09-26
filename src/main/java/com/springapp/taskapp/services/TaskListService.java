package com.springapp.taskapp.services;

import com.springapp.taskapp.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listTaskList(); // list every tasklist in our domain
    TaskList createTaskList(TaskList taskList); // create a new
    Optional<TaskList> getTaskList(UUID id);
    TaskList updateTaskList(UUID task_list_id, TaskList taskList); // id to find, tasklist to overwrite
    void deleteTaskList(UUID task_list_id);

    interface TaskService {
    }
}
