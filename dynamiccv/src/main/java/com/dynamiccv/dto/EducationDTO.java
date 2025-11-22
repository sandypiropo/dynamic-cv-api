package com.dynamiccv.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO {

    private Long id;

    @NotBlank(message = "{education.courseName.required}")
    private String courseName;

    @NotBlank(message = "{education.institution.required}")
    private String institution;

    @NotBlank(message = "{education.startYear.required}")
    private String startYear;

    @NotBlank(message = "{education.endYear.required}")
    private String endYear;

    private Long userId;

    public EducationDTO(Long id, String courseName, String institution, String startYear, String endYear) {
        this.id = id;
        this.courseName = courseName;
        this.institution = institution;
        this.startYear = startYear;
        this.endYear = endYear;
    }

}
