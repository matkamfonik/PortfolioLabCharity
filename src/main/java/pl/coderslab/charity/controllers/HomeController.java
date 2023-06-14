package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dtos.InstitutionDTO;
import pl.coderslab.charity.mappers.InstitutionMapper;
import pl.coderslab.charity.services.interfaces.DonationService;
import pl.coderslab.charity.services.interfaces.InstitutionService;

import javax.transaction.Transactional;
import java.util.List;


@Controller
@Transactional
@RequiredArgsConstructor
public class HomeController {

    private final DonationService donationService;

    private final InstitutionService institutionService;

    private final InstitutionMapper institutionMapper;


    @RequestMapping("/")
    public String homeAction(Model model){
        Integer donationQuantity = donationService.sum();
        Integer donationCount = donationService.count();
        List<InstitutionDTO> institutions = institutionService.findAll().stream().map(institutionMapper::toDto).toList();
        model.addAttribute("donationQuantity", donationQuantity);
        model.addAttribute("donationCount", donationCount);
        model.addAttribute("institutions", institutions);
        return "index";
    }
}
