package pl.coderslab.charity.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class DonationDTO {

    private Long id;

    @Min(value = 1)
    @NotNull
    private Integer quantity;

    @Size(min = 1)
    private Set<Long> categoriesIds = new HashSet<>();

    private List<String> categoriesNames = new ArrayList<>();

    @NotNull
    private Long institutionId;

    private String institutionName;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate pickUpDate;

    @NotNull
    private LocalTime pickUpTime;

    private String pickUpComment;

    private Long userId;

    private String statusName;

    private LocalDateTime created;

    private LocalDateTime received;

    private LocalDateTime transferred;

}
