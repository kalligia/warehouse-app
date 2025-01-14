package gr.giatzi.warehouseapp.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code DateService} class provides retrieves the current date
 * formatted as a string.
 */
@Service
public class DateService {
    public String getCurrentDate() {

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(formatter);
    }
}

