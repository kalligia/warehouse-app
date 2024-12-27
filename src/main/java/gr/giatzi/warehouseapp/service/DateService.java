package gr.giatzi.warehouseapp.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateService {

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}

