package gr.giatzi.warehouseapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 *  Handles user login requests. The user is presented with the login page
 *  or redirected to the home page if they are already authenticated.
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Principal principal) {
        return principal == null ? "login" : "redirect:/";
    }

}
