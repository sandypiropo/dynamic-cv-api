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

    private Long id;

    @NotBlank(message = "{experience.companyName.required}")
    private String companyName;

    @NotBlank(message = "{experience.position.required}")
    private String position;

    @NotBlank(message = "{experience.period.required}")
    private String period;

    @NotBlank(message = "{experience.description.required}")
    private String description;

    private String achievements;

    private Long userId;

    public ExperienceDTO(Long id, String companyName, String position, String period,
                         String description, String achievements) {
        this.id = id;
        this.companyName = companyName;
        this.position = position;
        this.period = period;
        this.description = description;
        this.achievements = achievements;
    }
}
