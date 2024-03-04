package com.example.taskmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Enable scheduling annotations
public class TaskMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskMasterApplication.class, args);
	}

}
