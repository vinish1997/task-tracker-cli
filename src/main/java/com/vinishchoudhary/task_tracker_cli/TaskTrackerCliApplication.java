package com.vinishchoudhary.task_tracker_cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.command.annotation.EnableCommand;

@SpringBootApplication
@CommandScan
public class TaskTrackerCliApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskTrackerCliApplication.class, args);
	}

}
