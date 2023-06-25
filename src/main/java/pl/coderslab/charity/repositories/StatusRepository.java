package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}