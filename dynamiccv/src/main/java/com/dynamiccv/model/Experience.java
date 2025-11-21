package com.dynamiccv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "experiences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Obrigatórios
    @NotBlank(message = "{experience.companyName.required}")
    private String companyName;

    @NotBlank(message = "{experience.position.required}")
    private String position;

    @NotBlank(message = "{experience.period.required}")
    private String period; // ex: jan 2024 — atual

    @NotBlank(message = "{experience.description.required}")
    private String description;

    // Opcional
    private String achievements;
}
