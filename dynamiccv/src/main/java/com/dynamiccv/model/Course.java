package com.dynamiccv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Obrigatórios
    @NotBlank(message = "{course.courseName.required}")
    private String courseName;

    @NotBlank(message = "{course.institution.required}")
    private String institution;

    // Opcional
    private String year; // ex: 2024

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
