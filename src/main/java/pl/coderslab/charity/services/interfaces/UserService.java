package pl.coderslab.charity.services.interfaces;

import pl.coderslab.charity.entities.Role;
import pl.coderslab.charity.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByEmail(String email);

    void saveNewUser(User user);

    void saveNewAdmin(User user);

    void saveUser(User user);

    void changePassword(User user);

    Optional<User> findById(Long id);

    void disable(Long id);

    void delete(Long id);

    List<User> findAllByRole(Role role);
} 