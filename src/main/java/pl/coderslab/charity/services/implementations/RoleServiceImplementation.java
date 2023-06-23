package pl.coderslab.charity.services.implementations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.Role;
import pl.coderslab.charity.repositories.RoleRepository;
import pl.coderslab.charity.services.interfaces.RoleService;

import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
