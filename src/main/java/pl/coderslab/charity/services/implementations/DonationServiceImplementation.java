package pl.coderslab.charity.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.services.interfaces.DonationService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonationServiceImplementation implements DonationService {
    private final DonationRepository donationRepository;


    @Override
    public Optional<Donation> find(Long id) {
        return donationRepository.findById(id);
    }

    @Override
    public Integer sum() {
        Integer sum = donationRepository.sumDonations();
        if (sum == null){
            sum = 0;
        }
        return sum;
    }

    @Override
    public Integer count(){
        return donationRepository.countAll();
    }

    @Override
    public void save(Donation donation) {
        donationRepository.save(donation);
    }
}
