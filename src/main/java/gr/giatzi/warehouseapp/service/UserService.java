package gr.giatzi.warehouseapp.service;


import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.UserReadOnlyDTO;
import gr.giatzi.warehouseapp.mapper.Mapper;
import gr.giatzi.warehouseapp.model.User;
import gr.giatzi.warehouseapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Page<UserReadOnlyDTO> getPaginatedUsers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.map(mapper::mapToUserReadOnlyDTO);
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUserOpt = userRepository.findByUsername(currentUsername);


        User currentUser = currentUserOpt.orElseThrow(() ->
                new EntityNotFoundException("User", "User not found"));

        if (currentUser.getId().equals(id)) {
            throw new IllegalArgumentException("Admins cannot delete their own account.");
        }

        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }
}