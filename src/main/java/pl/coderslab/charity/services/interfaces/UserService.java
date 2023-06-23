package pl.coderslab.charity.services.interfaces;

import pl.coderslab.charity.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByEmail(String email);

    void saveNewUser(User user);

    void saveUser(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    void delete(Long id);
} 