package pl.coderslab.charity.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class InstitutionDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
