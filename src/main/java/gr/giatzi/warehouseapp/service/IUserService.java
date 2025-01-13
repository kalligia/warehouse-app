package gr.giatzi.warehouseapp.service;

import gr.giatzi.warehouseapp.core.exceptions.EntityNotFoundException;
import gr.giatzi.warehouseapp.dto.UserReadOnlyDTO;
import gr.giatzi.warehouseapp.model.User;
import org.springframework.data.domain.Page;

public interface IUserService {

    User saveUser(User user);
    Page<UserReadOnlyDTO> getPaginatedUsers(int page, int size);
    void deleteUser(Long id) throws EntityNotFoundException;


}
