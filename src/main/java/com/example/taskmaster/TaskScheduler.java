package com.example.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TaskScheduler {

    private final RestTemplate restTemplate;

    @Autowired
    public TaskScheduler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 5000) // Execute every 5 seconds
    public void scheduleTask() {
 
        String apiUrl = "https://api.example.com/data";
        String responseData = restTemplate.getForObject(apiUrl, String.class);

 
        if (responseData != null && !responseData.isEmpty()) {
            processData(responseData);
        } else {
            System.out.println("Failed to fetch data from API.");
        }
    }

    private void processData(String data) {

        System.out.println("Processing data: " + data);
      
    }
}
