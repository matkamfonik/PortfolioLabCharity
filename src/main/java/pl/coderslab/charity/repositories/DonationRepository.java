package pl.coderslab.charity.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entities.Donation;

import java.util.List;


public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Integer sumDonations();

    @Query("SELECT COUNT(d) FROM Donation d")
    Integer countAll();

    @Override
    @Query("SELECT d FROM Donation d ORDER BY d.status.id ASC, d.received ASC, d.created ASC")
    List<Donation> findAll();

}
