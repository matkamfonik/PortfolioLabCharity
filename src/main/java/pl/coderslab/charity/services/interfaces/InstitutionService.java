package pl.coderslab.charity.services.interfaces;

import pl.coderslab.charity.entities.Institution;

import java.util.List;
import java.util.Optional;

public interface InstitutionService {
    List<Institution> findAll();

    Optional<Institution> findById(Long id);

    void delete(Long id);

    void saveInstitution(Institution institution);
}
