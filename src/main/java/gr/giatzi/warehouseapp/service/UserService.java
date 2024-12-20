package gr.giatzi.warehouseapp.service;


import gr.giatzi.warehouseapp.model.User;
import gr.giatzi.warehouseapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(rollbackOn = Exception.class)
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}