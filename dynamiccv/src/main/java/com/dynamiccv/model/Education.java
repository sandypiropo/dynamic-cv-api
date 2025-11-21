package com.dynamiccv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "education")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Obrigatórios
    @NotBlank(message = "{education.courseName.required}")
    private String courseName;

    @NotBlank(message = "{education.institution.required}")
    private String institution;

    @NotBlank(message = "{education.startYear.required}")
    private String startYear;

    // Obrigatório (ou pode ser previsão de término)
    @NotBlank(message = "{education.endYear.required}")
    private String endYear;
}
