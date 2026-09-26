package com.springapp.taskapp.services.impl;

import com.springapp.taskapp.domain.entities.TaskList;
import com.springapp.taskapp.repositories.TaskListRepository;
import com.springapp.taskapp.services.TaskListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository){
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskList(){
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null != taskList.getId()){
            throw new  IllegalArgumentException("Task List already has an ID");
        }
        if(null == taskList.getTitle() || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task List title must be present");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
                )
        );

    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID task_list_id, TaskList taskList) {
        if(null == taskList.getId()){
            throw new IllegalArgumentException("Task must have and id");
        }
        if(!Objects.equals(task_list_id, taskList.getId())){
            throw new IllegalArgumentException("Task list Id's must match");
        }

        TaskList existingTaskList = taskListRepository.findById(task_list_id).orElseThrow(() -> new IllegalArgumentException("Task list not found"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);

    }

    @Override
    public void deleteTaskList(UUID task_list_id) {
        taskListRepository.deleteById(task_list_id); // JPA deleteById handles that the ID may not exist
    }
}


