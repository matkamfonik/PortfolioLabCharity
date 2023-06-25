package pl.coderslab.charity.services.implementations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.Status;
import pl.coderslab.charity.repositories.StatusRepository;
import pl.coderslab.charity.services.interfaces.StatusService;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class StatusServiceImplementation implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Optional<Status> findById(Long id) {
        return statusRepository.findById(id);
    }

}
