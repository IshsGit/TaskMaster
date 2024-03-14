package com.example.taskmaster;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TaskScheduler {

    private static final Logger LOGGER = Logger.getLogger(TaskScheduler.class.getName());
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String APPLICATION_NAME = "Task Scheduler";

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 5000) 
    public void scheduleTask() {
        LOGGER.log(Level.INFO, "Executing every 5 seconds - March 5th, 2024");

        try {
           
            Calendar service = initializeCalendarService();

 
            Events events = service.events().list("primary").execute();
            List<Event> items = events.getItems();

        
            for (Event event : items) {
                processEvent(event);
            }
        } catch (IOException | GeneralSecurityException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch events from Google Calendar.", e);
        }
    }

    private Calendar initializeCalendarService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Calendar.Builder(httpTransport, JSON_FACTORY, getCredential(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private HttpRequestInitializer getCredential(HttpTransport httpTransport) throws IOException {
    
        return null;
    }

    private void processEvent(Event event) {
        LOGGER.log(Level.INFO, "Event: " + event.getSummary());
      
    }
}
