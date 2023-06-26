package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.dtos.CategoryDTO;
import pl.coderslab.charity.dtos.InstitutionDTO;
import pl.coderslab.charity.entities.Category;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.mappers.CategoryMapper;
import pl.coderslab.charity.mappers.InstitutionMapper;
import pl.coderslab.charity.services.interfaces.CategoryService;
import pl.coderslab.charity.services.interfaces.InstitutionService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    private final InstitutionMapper institutionMapper;

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @ModelAttribute("categories")
    public List<CategoryDTO> categories() {
        return categoryService.findAll().stream().map(categoryMapper::toDto).toList();
    }

    @GetMapping("admins/institutions/all")
    public String showInstitutions(Model model) {
        List<InstitutionDTO> institutionDTOS = institutionService.findAll().stream().map(institutionMapper::toDto).toList();
        model.addAttribute("institutions", institutionDTOS);
        return "admins/institutions";
    }

    @GetMapping("admins/institutions/{id}")
    public String showInstitution(Model model,
                                  @PathVariable(name = "id") Long id) {
        Institution institution = institutionService.findById(id).orElseThrow(EntityNotFoundException::new);
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);
        model.addAttribute("institution", institutionDTO);
        return "admins/institution";
    }

    @GetMapping("admins/institutions/{id}/delete")
    public String deleteInstitution(@PathVariable(name = "id") Long id) {
        institutionService.delete(id);
        return "redirect:/admins/institutions/all";
    }

    @GetMapping("admins/institutions/{id}/edit")
    public String editInstitution(Model model,
                                  @PathVariable(name = "id") Long id) {
        Institution institution = institutionService.findById(id).orElseThrow(EntityNotFoundException::new);
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);
        model.addAttribute("institution", institutionDTO);
        return "admins/institutionForm";
    }

    @PostMapping("admins/institutions/{id}/edit")
    public String editInstitution(@ModelAttribute(name = "institution") @Valid InstitutionDTO institutionDTO,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "admins/institutions/" + institutionDTO.getId() + "/edit";
        }
        Institution institution = institutionMapper.toEntity(institutionDTO);
        Set<Category> categories = institutionDTO.getCategoriesIds().stream().map(categoryService::findById).map(Optional::get).collect(Collectors.toSet());
        institution.setCategories(categories);
        institutionService.saveInstitution(institution);

        return "redirect:/admins/institutions/all";
    }

    @GetMapping("admins/institutions")
    public String addInstitution(Model model) {
        InstitutionDTO institutionDTO = new InstitutionDTO();
        model.addAttribute("institution", institutionDTO);
        return "admins/institutionForm";
    }

    @PostMapping("admins/institutions")
    public String addInstitution(@ModelAttribute(name = "institution") @Valid InstitutionDTO institutionDTO,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "admins/institutions";
        }
        Institution institution = institutionMapper.toEntity(institutionDTO);
        Set<Category> categories = institutionDTO.getCategoriesIds().stream().map(categoryService::findById).map(Optional::get).collect(Collectors.toSet());
        institution.setCategories(categories);
        institutionService.saveInstitution(institution);

        return "redirect:/admins/institutions/all";
    }

}
