package gr.giatzi.warehouseapp.controller;

import gr.giatzi.warehouseapp.dto.UserInsertDTO;
import gr.giatzi.warehouseapp.mapper.Mapper;
import gr.giatzi.warehouseapp.model.User;
import gr.giatzi.warehouseapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Mapper mapper;

    @GetMapping("/users/register")
    public String getUserForm(Model model) {
        model.addAttribute("userInsertDTO", new UserInsertDTO());
        return "user-form";
    }

    @PostMapping("/users/register")
    public String insertUser(@Valid @ModelAttribute("userInsertDTO")
                             UserInsertDTO userInsertDTO,
                             BindingResult bindingResult,
                             Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "user-form";
        }

        User user = mapper.mapToUserEntity(userInsertDTO);
        userService.saveUser(user);
        return "login";
    }
}
