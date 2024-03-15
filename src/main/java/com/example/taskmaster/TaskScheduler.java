import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

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

    private Credential getCredential(HttpTransport httpTransport) throws IOException {
       
        InputStream in = TaskScheduler.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

      
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, Collections.singletonList(CalendarScopes.CALENDAR))
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .build();

       
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    private void processEvent(Event event) {
        LOGGER.log(Level.INFO, "Event: " + event.getSummary());
        LOGGER.log(Level.INFO, "Location: " + event.getLocation());
        LOGGER.log(Level.INFO, "Start Time: " + event.getStart().getDateTime());
        LOGGER.log(Level.INFO, "End Time: " + event.getEnd().getDateTime());
        LOGGER.log(Level.INFO, "Description: " + event.getDescription());
     
    }
}
