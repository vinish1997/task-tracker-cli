package com.vinishchoudhary.task_tracker_cli.services;

import com.vinishchoudhary.task_tracker_cli.domain.TaskStatus;
import com.vinishchoudhary.task_tracker_cli.domain.Task;
import com.vinishchoudhary.task_tracker_cli.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.util.List;
import java.util.Objects;

@Command(command = "task-cli")
@Slf4j
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Command(command = "list")
    public List<Task> getAllTasks(@Option(required = false) String status){
        if (Objects.nonNull(status)) return taskRepository.getTasksByStatus(status);
        return taskRepository.getAllTasks();
    }

    @Command(command = "add")
    public Task createTask(@Option(required = true) String description){
        Task newTask = Task.builder().description(description).status(TaskStatus.TO_DO).build();
        return taskRepository.createTask(newTask);
    }

    @Command(command = "update")
    public Task updateTask(@Option(required = true) Integer id, @Option(required = true) String description){
        return taskRepository.updateTaskDescription(id, description);
    }

    @Command(command = "mark-in-progress")
    public Task markInProgress(@Option(required = true) Integer id){
        try {
            return taskRepository.updateTaskStatus(id, TaskStatus.IN_PROGRESS);
        } catch (RuntimeException e) {
            log.error("Task not found", e);
            return null;
        }
    }

    @Command(command = "mark-done")
    public Task markDone(@Option(required = true) Integer id){
        try {
            return taskRepository.updateTaskStatus(id, TaskStatus.DONE);
        } catch (RuntimeException e) {
            log.error("Task not found", e);
            return null;
        }
    }

    @Command(command = "delete")
    public String deleteTask(@Option(required = true) Integer id){
        try {
            taskRepository.deleteTask(id);
            return "Deletion successful";
        } catch (RuntimeException e) {
            return "Not able to delete";
        }
    }

    @Command(command = "get")
    public Task getTask(@Option(required = true) Integer id){
        return taskRepository.getTaskById(id);
    }
}
