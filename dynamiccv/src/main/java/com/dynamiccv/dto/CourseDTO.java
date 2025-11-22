package com.dynamiccv.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;

    @NotBlank(message = "{course.courseName.required}")
    private String courseName;

    @NotBlank(message = "{course.institution.required}")
    private String institution;

    private String year;
}
