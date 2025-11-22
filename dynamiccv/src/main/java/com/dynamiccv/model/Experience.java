package com.dynamiccv.model;

import jakarta.persistence.*;
        import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "{experience.companyName.required}")
    private String companyName;

    @NotBlank(message = "{experience.position.required}")
    private String position;

    @NotBlank(message = "{experience.period.required}")
    private String period;

    @NotBlank(message = "{experience.description.required}")
    private String description;

    private String achievements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
