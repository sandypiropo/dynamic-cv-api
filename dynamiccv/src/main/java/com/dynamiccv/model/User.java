package com.dynamiccv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{user.name.required}")
    private String fullName;

    @NotBlank(message = "{user.email.required}")
    @Email(message = "{user.email.invalid}")
    private String email;

    @NotBlank(message = "{user.phone.required}")
    private String phone;

    // Cidade / Estado / País obrigatórios
    @NotBlank(message = "{user.city.required}")
    private String city;

    @NotBlank(message = "{user.state.required}")
    private String state;

    @NotBlank(message = "{user.country.required}")
    private String country;

    // Opcional
    private String linkedin;

    // Opcional
    private String github;

    // Obrigatório
    @NotBlank(message = "{user.objective.required}")
    private String objective;

    // Hard skills obrigatório ter pelo menos 1
    @ElementCollection
    @NotEmpty(message = "{user.hardskills.required}")
    private List<String> hardSkills;

    // Soft skills opcional
    @ElementCollection
    private List<String> softSkills;

    // Experiências podem estar vazias
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Experience> experiences;

    // Educação pode estar vazia
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Education> education;

    // Cursos podem estar vazios
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Course> courses;

    // Idiomas opcionais
    @ElementCollection
    private List<String> languages;
}
