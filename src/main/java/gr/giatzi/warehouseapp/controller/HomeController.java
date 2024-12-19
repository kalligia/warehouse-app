package gr.giatzi.warehouseapp.controller;

import org.springframework.ui.Model;
import gr.giatzi.warehouseapp.service.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final DateService dateService;

    @GetMapping("/")
    public String index(Model model) {
        LocalDate currentDate = dateService.getCurrentDate();

        // Add the current date to the model to pass it to the Thymeleaf view
        model.addAttribute("currentDate", currentDate);
        // Return the name of the Thymeleaf template (without the .html extension)
        return "index";
    }

}