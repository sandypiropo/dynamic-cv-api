package com.dynamiccv.service;

import com.dynamiccv.dto.EducationDTO;
import com.dynamiccv.model.Education;
import com.dynamiccv.model.User;
import com.dynamiccv.repository.EducationRepository;
import com.dynamiccv.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    public EducationService(EducationRepository educationRepository, UserRepository userRepository) {
        this.educationRepository = educationRepository;
        this.userRepository = userRepository;
    }

    public List<EducationDTO> getAllEducations(Long userId) {
        List<Education> educations;
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
            educations = user.getEducation();
        } else {
            educations = educationRepository.findAll();
        }

        return educations.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EducationDTO getEducationById(Long id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found with id: " + id));
        return mapToDTO(education);
    }

    public EducationDTO createEducation(EducationDTO dto) {
        Education education = mapToEntity(dto);
        Education saved = educationRepository.save(education);
        return mapToDTO(saved);
    }

    public EducationDTO updateEducation(Long id, EducationDTO dto) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found with id: " + id));

        education.setCourseName(dto.getCourseName());
        education.setInstitution(dto.getInstitution());
        education.setStartYear(dto.getStartYear());
        education.setEndYear(dto.getEndYear());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
            education.setUser(user);
        }

        Education updated = educationRepository.save(education);
        return mapToDTO(updated);
    }

    public void deleteEducation(Long id) {
        if (!educationRepository.existsById(id)) {
            throw new RuntimeException("Education not found with id: " + id);
        }
        educationRepository.deleteById(id);
    }

    private EducationDTO mapToDTO(Education entity) {
        EducationDTO dto = new EducationDTO();
        dto.setId(entity.getId());
        dto.setCourseName(entity.getCourseName());
        dto.setInstitution(entity.getInstitution());
        dto.setStartYear(entity.getStartYear());
        dto.setEndYear(entity.getEndYear());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        return dto;
    }

    private Education mapToEntity(EducationDTO dto) {
        Education education = new Education();
        education.setCourseName(dto.getCourseName());
        education.setInstitution(dto.getInstitution());
        education.setStartYear(dto.getStartYear());
        education.setEndYear(dto.getEndYear());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
            education.setUser(user);
        }

        return education;
    }
}
