package pl.coderslab.charity.services.interfaces;

import pl.coderslab.charity.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findById(Long id);
}
