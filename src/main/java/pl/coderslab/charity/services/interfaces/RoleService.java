package pl.coderslab.charity.services.interfaces;

import pl.coderslab.charity.entities.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findByName(String name);
}
