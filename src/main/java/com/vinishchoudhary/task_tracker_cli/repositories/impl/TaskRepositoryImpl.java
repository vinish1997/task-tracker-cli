package com.vinishchoudhary.task_tracker_cli.repositories.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vinishchoudhary.task_tracker_cli.constants.TaskStatus;
import com.vinishchoudhary.task_tracker_cli.domain.Task;
import com.vinishchoudhary.task_tracker_cli.exception.TaskNotFoundException;
import com.vinishchoudhary.task_tracker_cli.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
public class TaskRepositoryImpl implements TaskRepository {

    private List<Task> tasks;
    private final File jsonFile = new File("tasks.json");
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public TaskRepositoryImpl(){
        load();
    }

    private void load(){
        try {
            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
            }
            else if (jsonFile.length() > 0){
                tasks = mapper.readValue(jsonFile, new TypeReference<List<Task>>() {});
            }
            tasks = new ArrayList<>();
        } catch (IOException e) {
            log.error("File not found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> getAllTask() {
        return tasks;
    }

    @Override
    public List<Task> getTaskByStatus(String status){
        return tasks.stream().filter(task -> task.getStatus().equalsStatus(status)).toList();
    }

    @Override
    public Task getTaskById(int id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        task.setId(generateNewId());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        tasks.add(task);
        writeToFile();
        return task;
    }

    @Override
    public Task updateTaskDescription(int id, String description) {
        Task oldTask = getTaskById(id);
        if (Objects.nonNull(oldTask)){
            oldTask.setDescription(description);
            oldTask.setUpdatedAt(LocalDateTime.now());
            writeToFile();
            return oldTask;
        } else {
            log.error("Task not found with id {}", id);
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
    }

    @Override
    public Task updateTaskStatus(int id, TaskStatus status) {
        Task oldTask = getTaskById(id);
        if (Objects.nonNull(oldTask)){
            oldTask.setStatus(status);
            oldTask.setUpdatedAt(LocalDateTime.now());
            writeToFile();
            return oldTask;
        } else {
            log.error("Task not found with id {}", id);
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
    }

    @Override
    public void deleteTask(int id) {
        boolean removed = tasks.removeIf(task -> task.getId().equals(id));
        if (removed) {
            writeToFile();
        } else {
            log.error("Task not found with id {}", id);
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
    }

    private void writeToFile(){
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int generateNewId(){
        return tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
    }
}
