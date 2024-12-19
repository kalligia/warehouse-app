package gr.giatzi.warehouseapp.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateService {

    // Method to get the current date
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}

