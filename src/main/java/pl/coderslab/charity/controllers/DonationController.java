package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dtos.CategoryDTO;
import pl.coderslab.charity.dtos.DonationDTO;
import pl.coderslab.charity.dtos.InstitutionDTO;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.mappers.CategoryMapper;
import pl.coderslab.charity.mappers.DonationMapper;
import pl.coderslab.charity.mappers.InstitutionMapper;
import pl.coderslab.charity.services.interfaces.CategoryService;
import pl.coderslab.charity.services.interfaces.DonationService;
import pl.coderslab.charity.services.interfaces.InstitutionService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/donations")
public class DonationController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    private final InstitutionService institutionService;

    private final InstitutionMapper institutionMapper;

    private final DonationService donationService;

    private final DonationMapper donationMapper;

    @ModelAttribute("categories")
    public List<CategoryDTO> categories(){
        return categoryService.findAll().stream().map(categoryMapper::toDto).toList();
    }

    @ModelAttribute("institutions")
    public List<InstitutionDTO> institutions(){
        return institutionService.findAll().stream().map(institutionMapper::toDto).toList();
    }

    @GetMapping("/new")
    public String newDonation(Model model) {

        DonationDTO donation = new DonationDTO();
        model.addAttribute("donation", donation);

        return "form";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute(name = "donation") @Valid DonationDTO donationDTO,
                      BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        Donation donation = donationMapper.toEntity(donationDTO);
        donation.setInstitution(institutionService.findById(donationDTO.getInstitutionId()).orElseThrow(EntityNotFoundException::new));
        donationDTO.getCategoriesIds().stream().map(categoryService::findById).forEach(c -> donation.getCategories().add(c.orElseThrow(EntityNotFoundException::new)));
        donationService.save(donation);
        return "form-confirmation";
    }
}
