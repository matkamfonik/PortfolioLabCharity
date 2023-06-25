package pl.coderslab.charity.services.interfaces;

import pl.coderslab.charity.entities.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {

    List<Status> findAll();

    Optional<Status> findById(Long id);

}
