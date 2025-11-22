package com.dynamiccv.controller;

import com.dynamiccv.dto.EducationDTO;
import com.dynamiccv.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/educations")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public List<EducationDTO> getAllEducations(@RequestParam(required = false) Long userId) {
        return educationService.getAllEducations(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationDTO> getEducationById(@PathVariable Long id) {
        EducationDTO dto = educationService.getEducationById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EducationDTO> createEducation(
            @RequestParam Long userId,
            @Valid @RequestBody EducationDTO dto) {
        EducationDTO created = educationService.createEducation(userId, dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationDTO> updateEducation(
            @PathVariable Long id,
            @RequestParam Long userId,
            @Valid @RequestBody EducationDTO dto) {

        EducationDTO updated = educationService.updateEducation(id, userId, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }
}
