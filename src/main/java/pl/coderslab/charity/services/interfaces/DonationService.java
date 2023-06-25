package pl.coderslab.charity.services.interfaces;

import pl.coderslab.charity.entities.Donation;

import java.util.List;
import java.util.Optional;

public interface DonationService {

    Optional<Donation> find(Long id);

    Integer sum();

    Integer count();

    void save(Donation donation);

    List<Donation> findAll();
}
