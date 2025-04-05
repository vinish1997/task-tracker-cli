package com.vinishchoudhary.task_tracker_cli.constants;


import lombok.Getter;

@Getter
public enum TaskStatus {
    TO_DO("todo"),
    IN_PROGRESS("in-progress"),
    DONE("done");

    private final String status;

    TaskStatus(String status){
        this.status = status;
    }

    public boolean equalsStatus(String status){
        return this.status.equals(status);
    }
}
