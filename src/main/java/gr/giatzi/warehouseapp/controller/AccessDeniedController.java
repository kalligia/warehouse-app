package gr.giatzi.warehouseapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Responsible for handling requests related to access denial.
 * It displays an error page when a user attempts to access a resource they do not have permission for.
 */
@Controller
public class AccessDeniedController {

    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("error", "You do not have permission to view this page.");
        return "access-denied";
    }
}

