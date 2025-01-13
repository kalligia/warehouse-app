package gr.giatzi.warehouseapp.controller;

import gr.giatzi.warehouseapp.service.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final DateService dateService;

    @GetMapping("/")
    public String index(Model model) {
        String currentDate = dateService.getCurrentDate();
        model.addAttribute("currentDate", currentDate);

        return "index";
    }
}