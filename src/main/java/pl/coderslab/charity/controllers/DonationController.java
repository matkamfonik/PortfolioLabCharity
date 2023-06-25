package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dtos.CategoryDTO;
import pl.coderslab.charity.dtos.DonationDTO;
import pl.coderslab.charity.dtos.InstitutionDTO;
import pl.coderslab.charity.entities.CurrentUser;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.mappers.CategoryMapper;
import pl.coderslab.charity.mappers.DonationMapper;
import pl.coderslab.charity.mappers.InstitutionMapper;
import pl.coderslab.charity.services.interfaces.CategoryService;
import pl.coderslab.charity.services.interfaces.DonationService;
import pl.coderslab.charity.services.interfaces.InstitutionService;
import pl.coderslab.charity.services.interfaces.StatusService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("")
public class DonationController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    private final InstitutionService institutionService;

    private final InstitutionMapper institutionMapper;

    private final DonationService donationService;

    private final DonationMapper donationMapper;

    private final StatusService statusService;

    @ModelAttribute("categories")
    public List<CategoryDTO> categories() {
        return categoryService.findAll().stream().map(categoryMapper::toDto).toList();
    }

    @ModelAttribute("institutions")
    public List<InstitutionDTO> institutions() {
        return institutionService.findAll().stream().map(institutionMapper::toDto).toList();
    }

    @GetMapping("/donations/new")
    public String newDonation(Model model) {

        DonationDTO donation = new DonationDTO();
        model.addAttribute("donation", donation);

        return "form";
    }

    @PostMapping("/donations/new")
    public String add(@ModelAttribute(name = "donation") @Valid DonationDTO donationDTO,
                      BindingResult result,
                      @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "form";
        }
        Donation donation = donationMapper.toEntity(donationDTO);
        donation.setUser(currentUser.getUser());
        donation.setInstitution(institutionService.findById(donationDTO.getInstitutionId()).orElseThrow(EntityNotFoundException::new));
        donationDTO.getCategoriesIds().stream().map(categoryService::findById).forEach(c -> donation.getCategories().add(c.orElseThrow(EntityNotFoundException::new)));
        donation.setStatus(statusService.findById(1L).get());
        donationService.save(donation);
        return "form-confirmation";
    }

    @GetMapping("admins/donations/all")
    public String showAll(Model model) {
        List<Donation> donations = donationService.findAll();
        List<DonationDTO> donationDTOS = donations.stream().map(donationMapper::toDto).toList();
        model.addAttribute("donations", donationDTOS);
        return "admins/donations";
    }

    @GetMapping("admins/donations/{id}/set-received")
    public String setReceived(Model model,
                              @PathVariable(name = "id") Long id) {
        Donation donation = donationService.find(id).orElseThrow(EntityNotFoundException::new);
        donation.setReceived(LocalDateTime.now());
        donation.setStatus(statusService.findById(2L).get());
        donationService.save(donation);
        List<Donation> donations = donationService.findAll();
        List<DonationDTO> donationDTOS = donations.stream().map(donationMapper::toDto).toList();
        model.addAttribute("donations", donationDTOS);
        return "admins/donations";
    }

    @GetMapping("admins/donations/{id}/set-transferred")
    public String setTransferred(Model model,
                                 @PathVariable(name = "id") Long id) {
        Donation donation = donationService.find(id).orElseThrow(EntityNotFoundException::new);
        donation.setTransferred(LocalDateTime.now());
        donation.setStatus(statusService.findById(3L).get());
        donationService.save(donation);
        List<Donation> donations = donationService.findAll();
        List<DonationDTO> donationDTOS = donations.stream().map(donationMapper::toDto).toList();
        model.addAttribute("donations", donationDTOS);
        return "admins/donations";
    }
}
