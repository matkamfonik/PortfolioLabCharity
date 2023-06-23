package pl.coderslab.charity.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class UserDTO {

    private Long id;

    @NotBlank
    private String email;

    private int enabled;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    private Set<String> rolesNames = new HashSet<>();
}
