package com.example.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TaskScheduler {

    private static final Logger LOGGER = Logger.getLogger(TaskScheduler.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 5000) // Execute every 5 seconds
    public void scheduleTask() {
        LOGGER.log(Level.INFO, "Executing every 5 seconds - March 5th, 2024");

     
        String apiUrl = "";
        try {
            String responseData = restTemplate.getForObject(apiUrl, String.class);

      
            processDataAsync(responseData);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch data from API.", e);
        }
    }

    private void processDataAsync(String data) {
   
        new Thread(() -> {
            LOGGER.log(Level.INFO, "Processing data: " + data);
 
        }).start();
    }
}
