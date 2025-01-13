package gr.giatzi.warehouseapp.controller;

import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.UserInsertDTO;
import gr.giatzi.warehouseapp.dto.UserReadOnlyDTO;
import gr.giatzi.warehouseapp.mapper.Mapper;
import gr.giatzi.warehouseapp.model.User;
import gr.giatzi.warehouseapp.repository.UserRepository;
import gr.giatzi.warehouseapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Mapper mapper;
    private final UserRepository userRepository;

    @GetMapping("/users/register")
    public String getUserForm(Model model) {
        model.addAttribute("userInsertDTO", new UserInsertDTO());
        return "register";
    }

    @PostMapping("/users/register")
    public String insertUser(@Valid @ModelAttribute("userInsertDTO")
                             UserInsertDTO userInsertDTO,
                             BindingResult bindingResult,
                             Model model, RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        redirectAttributes.addFlashAttribute("successMessage", "User registered successfully.");
        User user = mapper.mapToUserEntity(userInsertDTO);
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String getPaginatedUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) throws EntityNotFoundException {

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUserOptional = userRepository.findByUsername(currentUsername);
        User currentUser = currentUserOptional.orElseThrow(() -> new EntityNotFoundException("Current user not found", currentUsername));

        Page<UserReadOnlyDTO> usersPage = userService.getPaginatedUsers(page, size);

        model.addAttribute("currentUserId", currentUser.getId());
        model.addAttribute("usersPage", usersPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usersPage.getTotalPages());

        return "users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes)
            throws EntityNotFoundException {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        return "redirect:/warehouse/users";
    }
}
