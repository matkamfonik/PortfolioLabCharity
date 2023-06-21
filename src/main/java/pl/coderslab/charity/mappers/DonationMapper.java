package pl.coderslab.charity.mappers;

import org.springframework.stereotype.Component;
import pl.coderslab.charity.dtos.DonationDTO;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Donation;

@Component
public class DonationMapper {
    public Donation toEntity(DonationDTO donationDTO){
        Donation donation = new Donation();
        donation.setQuantity(donationDTO.getQuantity());
        donation.setStreet(donationDTO.getStreet());
        donation.setCity(donationDTO.getCity());
        donation.setZipCode(donationDTO.getZipCode());
        donation.setPickUpDateTime(donationDTO.getPickUpDate().atTime(donationDTO.getPickUpTime()));
        donation.setPickUpComment(donationDTO.getPickUpComment());
        return donation;
    }

    public DonationDTO toDto(Donation donation){
        DonationDTO donationDTO = new DonationDTO();
        donationDTO.setQuantity(donation.getQuantity());
        donation.getCategories()
                .stream()
                .map(Category::getId)
                .forEach(ci -> donationDTO.getCategoriesIds().add(ci));
        donationDTO.setInstitutionId(donation.getInstitution().getId());
        donationDTO.setStreet(donation.getStreet());
        donationDTO.setCity(donation.getCity());
        donationDTO.setZipCode(donation.getZipCode());
        donationDTO.setPickUpDate(donation.getPickUpDateTime().toLocalDate());
        donationDTO.setPickUpTime(donation.getPickUpDateTime().toLocalTime());
        donationDTO.setPickUpComment(donation.getPickUpComment());
        donationDTO.setUserId(donation.getUser().getId());
        return donationDTO;
    }
}
