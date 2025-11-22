package com.dynamiccv.service;

import com.dynamiccv.dto.ExperienceDTO;
import com.dynamiccv.model.Experience;
import com.dynamiccv.model.User;
import com.dynamiccv.repository.ExperienceRepository;
import com.dynamiccv.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;

    public ExperienceService(ExperienceRepository experienceRepository, UserRepository userRepository) {
        this.experienceRepository = experienceRepository;
        this.userRepository = userRepository;
    }

    // GET all experiences, optionally filtered by user
    public List<ExperienceDTO> getAllExperiences(Long userId) {
        List<Experience> experiences;
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            experiences = user.getExperiences();
        } else {
            experiences = experienceRepository.findAll();
        }
        return experiences.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // GET by id
    public ExperienceDTO getExperienceById(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));
        return mapToDTO(experience);
    }

    // CREATE
    public ExperienceDTO createExperience(ExperienceDTO dto) {
        Experience experience = mapToEntity(dto);

        if (dto.getUserId() != null) { // supondo que ExperienceDTO tenha userId
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            experience.setUser(user);
        }

        Experience saved = experienceRepository.save(experience);
        return mapToDTO(saved);
    }

    // UPDATE
    public ExperienceDTO updateExperience(Long id, ExperienceDTO dto) {
        Experience existing = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));
        existing.setCompanyName(dto.getCompanyName());
        existing.setPosition(dto.getPosition());
        existing.setPeriod(dto.getPeriod());
        existing.setDescription(dto.getDescription());
        existing.setAchievements(dto.getAchievements());
        Experience updated = experienceRepository.save(existing);
        return mapToDTO(updated);
    }

    // DELETE
    public void deleteExperience(Long id) {
        Experience existing = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));
        experienceRepository.delete(existing);
    }

    // Mappers
    private ExperienceDTO mapToDTO(Experience experience) {
        ExperienceDTO dto = new ExperienceDTO();
        dto.setId(experience.getId());
        dto.setCompanyName(experience.getCompanyName());
        dto.setPosition(experience.getPosition());
        dto.setPeriod(experience.getPeriod());
        dto.setDescription(experience.getDescription());
        dto.setAchievements(experience.getAchievements());
        return dto;
    }

    private Experience mapToEntity(ExperienceDTO dto) {
        Experience experience = new Experience();
        experience.setCompanyName(dto.getCompanyName());
        experience.setPosition(dto.getPosition());
        experience.setPeriod(dto.getPeriod());
        experience.setDescription(dto.getDescription());
        experience.setAchievements(dto.getAchievements());
        return experience;
    }
}
