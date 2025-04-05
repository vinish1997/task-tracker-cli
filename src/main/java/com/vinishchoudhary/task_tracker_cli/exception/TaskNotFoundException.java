package com.vinishchoudhary.task_tracker_cli.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException() {
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
