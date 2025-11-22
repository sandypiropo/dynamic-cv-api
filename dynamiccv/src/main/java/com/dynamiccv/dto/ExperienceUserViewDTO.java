package com.dynamiccv.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceUserViewDTO {

    private Long id;
    private String companyName;
    private String position;
    private String period;
    private String description;
    private String achievements;
}
