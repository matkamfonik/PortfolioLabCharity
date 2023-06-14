package pl.coderslab.charity.mappers;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.dtos.InstitutionDTO;
import pl.coderslab.charity.entities.Institution;

@Component
public class InstitutionMapper {
    public Institution toEntity(InstitutionDTO institutionDTO){
        Institution  institution = new Institution();
        institution.setId(institutionDTO.getId());
        institution.setName(institutionDTO.getName());
        institution.setDescription(institutionDTO.getDescription());
        return institution;
    }

    public InstitutionDTO toDto(Institution institution){
        InstitutionDTO institutionDTO = new InstitutionDTO();
        institutionDTO.setId(institution.getId());
        institutionDTO.setName(institution.getName());
        institutionDTO.setDescription(institution.getDescription());
        return institutionDTO;
    }
}
