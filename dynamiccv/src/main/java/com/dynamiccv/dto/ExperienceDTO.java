package com.dynamiccv.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperienceDTO {

    @NotBlank(message = "{experience.companyName.required}")
    private String companyName;

    @NotBlank(message = "{experience.position.required}")
    private String position;

    @NotBlank(message = "{experience.period.required}")
    private String period; // ex: jan 2024 — atual

    @NotBlank(message = "{experience.description.required}")
    private String description;

    private String achievements; // opcional
}
