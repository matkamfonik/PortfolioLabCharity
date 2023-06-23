package pl.coderslab.charity.mappers;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.dtos.UserDTO;
import pl.coderslab.charity.entities.Role;
import pl.coderslab.charity.entities.User;

@Component
public class UserMapper {

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    public User toEntity(UserDTO userDTO, User user) {
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEnabled(user.getEnabled());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        user.getRoles()
                .stream()
                .map(Role::getName)
                .forEach(rn -> userDTO.getRolesNames().add(rn));
        return userDTO;
    }

}
