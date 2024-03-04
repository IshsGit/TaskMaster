package com.example.taskmaster;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskScheduler {

    @Scheduled(fixedRate = 5000) 
    public void taskToExecute() {

        System.out.println("Executing scheduled task...");
    }
}
