package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.model.User;
import java.util.Optional;

public interface IUserService {

    Optional<User> findUserByUsername(String username);

    User saveUser(User user);
}
