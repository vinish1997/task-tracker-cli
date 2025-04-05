package com.vinishchoudhary.task_tracker_cli.repositories;

import com.vinishchoudhary.task_tracker_cli.constants.TaskStatus;
import com.vinishchoudhary.task_tracker_cli.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository {

    public List<Task> getAllTask();

    public List<Task> getTaskByStatus(String status);

    public Task getTaskById(int id);

    public Task createTask(Task task);

    public Task updateTaskDescription(int id, String description);

    public Task updateTaskStatus(int id, TaskStatus status);

    public void deleteTask(int id);
}
