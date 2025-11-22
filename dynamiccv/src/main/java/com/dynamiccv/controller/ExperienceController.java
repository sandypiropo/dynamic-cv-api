package com.dynamiccv.controller;

import com.dynamiccv.dto.ExperienceDTO;
import com.dynamiccv.service.ExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public List<ExperienceDTO> getAllExperiences(@RequestParam(required = false) Long userId) {
        return experienceService.getAllExperiences(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceDTO> getExperienceById(@PathVariable Long id) {
        ExperienceDTO dto = experienceService.getExperienceById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ExperienceDTO> createExperience(@Valid @RequestBody ExperienceDTO experienceDTO) {
        ExperienceDTO created = experienceService.createExperience(experienceDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienceDTO> updateExperience(
            @PathVariable Long id,
            @Valid @RequestBody ExperienceDTO experienceDTO) {
        ExperienceDTO updated = experienceService.updateExperience(id, experienceDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }
}
