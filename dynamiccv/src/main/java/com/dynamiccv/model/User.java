package com.dynamiccv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.ArrayList;
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

    @ElementCollection
    @NotEmpty(message = "{user.hardskills.required}")
    private List<String> hardSkills = new ArrayList<>();

    @ElementCollection
    private List<String> softSkills = new ArrayList<>();

    // Alteração: agora bidirecional, tabela filha controla a FK "user_id"
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences = new ArrayList<>();

    // Alteração: mesmo padrão do relacionamento Experience
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> education = new ArrayList<>();

    // Alteração: idem para Courses
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    @ElementCollection
    private List<String> languages = new ArrayList<>();
}
