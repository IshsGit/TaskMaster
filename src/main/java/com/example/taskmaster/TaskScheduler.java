package com.example.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TaskScheduler {

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 5000) // Execute every 5 seconds
    public void scheduleTask() {
        System.out.println("Executing every 5 seconds - March 5th, 2024");
        // Simulate fetching data from an external API
        String apiUrl = "https://api.example.com/data";
        String responseData = restTemplate.getForObject(apiUrl, String.class);

        // Process the retrieved data
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
