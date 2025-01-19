package gr.giatzi.warehouseapp.controller;

import gr.giatzi.warehouseapp.core.exceptions.EntityAlreadyExistsException;
import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.UserInsertDTO;
import gr.giatzi.warehouseapp.dto.UserReadOnlyDTO;
import gr.giatzi.warehouseapp.mapper.Mapper;
import gr.giatzi.warehouseapp.model.User;
import gr.giatzi.warehouseapp.repository.UserRepository;
import gr.giatzi.warehouseapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/users/register")
    public String getUserForm(Model model) {
        model.addAttribute("userInsertDTO", new UserInsertDTO());
        return "register";
    }

    @PostMapping("/users/register")
    public String insertUser(@Valid @ModelAttribute("userInsertDTO")
                             UserInsertDTO userInsertDTO,
                             BindingResult bindingResult,
                             Model model, RedirectAttributes redirectAttributes) {
        User savedUser;

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            savedUser = userService.saveUser(userInsertDTO);
            redirectAttributes.addFlashAttribute("successMessage", "User registered successfully.");
            LOGGER.info("User with id {} added", savedUser.getId());
        } catch (EntityAlreadyExistsException e) {
            LOGGER.error("User with username {} not added", userInsertDTO.getUsername());
            redirectAttributes.addFlashAttribute("warningMessage", "User with username " + userInsertDTO.getUsername() + " already exists. Please use another username.");
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/warehouse/users/register"; }

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
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {

        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
            return "redirect:/warehouse/users";
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error/item-not-found";
        }
    }
}
