package com.dynamiccv.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;

    @NotBlank(message = "{user.name.required}")
    private String fullName;

    @NotBlank(message = "{user.email.required}")
    @Email(message = "{user.email.invalid}")
    private String email;

    @NotBlank(message = "{user.phone.required}")
    private String phone;

    @NotBlank(message = "{user.city.required}")
    private String city;

    @NotBlank(message = "{user.state.required}")
    private String state;

    @NotBlank(message = "{user.country.required}")
    private String country;

    private String linkedin;
    private String github;

    @NotBlank(message = "{user.objective.required}")
    private String objective;

    @NotEmpty(message = "{user.hardskills.required}")
    private List<String> hardSkills = new ArrayList<>();

    private List<String> softSkills = new ArrayList<>();
    private List<ExperienceDTO> experiences = new ArrayList<>();
    private List<EducationDTO> education = new ArrayList<>();
    private List<CourseDTO> courses = new ArrayList<>();
    private List<String> languages = new ArrayList<>();
}
