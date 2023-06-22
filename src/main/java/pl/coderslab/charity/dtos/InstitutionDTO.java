package pl.coderslab.charity.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class InstitutionDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Size(min = 1)
    private Set<Long> categoriesIds = new HashSet<>();
}
