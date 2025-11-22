package com.dynamiccv.service;

import com.dynamiccv.dto.*;
import com.dynamiccv.model.*;
import com.dynamiccv.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private User mapToEntity(UserDTO dto) {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setCity(dto.getCity());
        user.setState(dto.getState());
        user.setCountry(dto.getCountry());
        user.setLinkedin(dto.getLinkedin());
        user.setGithub(dto.getGithub());
        user.setObjective(dto.getObjective());
        user.setHardSkills(dto.getHardSkills());
        user.setSoftSkills(dto.getSoftSkills());
        user.setLanguages(dto.getLanguages());

        // Mapeando experiências e setando o usuário
        if (dto.getExperiences() != null) {
            List<Experience> experiences = dto.getExperiences().stream()
                    .map(exp -> {
                        Experience e = Experience.builder()
                                .companyName(exp.getCompanyName())
                                .position(exp.getPosition())
                                .period(exp.getPeriod())
                                .description(exp.getDescription())
                                .achievements(exp.getAchievements())
                                .build();

                        // Necessário para popular a coluna user_id
                        e.setUser(user);

                        return e;
                    })
                    .toList();

            user.setExperiences(experiences);
        }

        // Mapeando educação e setando o usuário
        if (dto.getEducation() != null) {
            List<Education> education = dto.getEducation().stream()
                    .map(ed -> {
                        Education e = Education.builder()
                                .courseName(ed.getCourseName())
                                .institution(ed.getInstitution())
                                .startYear(ed.getStartYear())
                                .endYear(ed.getEndYear())
                                .build();

                        // Necessário para popular a coluna user_id
                        e.setUser(user);

                        return e;
                    })
                    .toList();

            user.setEducation(education);
        }

        // Mapeando cursos e setando o usuário
        if (dto.getCourses() != null) {
            List<Course> courses = dto.getCourses().stream()
                    .map(c -> {
                        Course course = Course.builder()
                                .courseName(c.getCourseName())
                                .institution(c.getInstitution())
                                .year(c.getYear())
                                .build();

                        // Necessário para popular a coluna user_id
                        course.setUser(user);

                        return course;
                    })
                    .toList();

            user.setCourses(courses);
        }

        return user;
    }

    private UserDTO mapToDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setCountry(entity.getCountry());
        dto.setLinkedin(entity.getLinkedin());
        dto.setGithub(entity.getGithub());
        dto.setObjective(entity.getObjective());
        dto.setHardSkills(entity.getHardSkills());
        dto.setSoftSkills(entity.getSoftSkills());
        dto.setLanguages(entity.getLanguages());

        // Converter experiências para DTO
        dto.setExperiences(
                entity.getExperiences() == null ? List.of() :
                        entity.getExperiences().stream()
                                .map(exp -> new ExperienceUserViewDTO(
                                        exp.getId(),
                                        exp.getCompanyName(),
                                        exp.getPosition(),
                                        exp.getPeriod(),
                                        exp.getDescription(),
                                        exp.getAchievements()
                                ))
                                .toList()
        );

        dto.setEducation(
                entity.getEducation() == null ? List.of() :
                        entity.getEducation().stream()
                                .map(ed -> new EducationDTO(
                                        ed.getId(),
                                        ed.getCourseName(),
                                        ed.getInstitution(),
                                        ed.getStartYear(),
                                        ed.getEndYear()
                                ))
                                .toList()
        );

        dto.setCourses(
                entity.getCourses() == null ? List.of() :
                        entity.getCourses().stream()
                                .map(c -> new CourseDTO(
                                        c.getId(),
                                        c.getCourseName(),
                                        c.getInstitution(),
                                        c.getYear()
                                ))
                                .toList()
        );

        return dto;
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        user.setCountry(userDTO.getCountry());
        user.setLinkedin(userDTO.getLinkedin());
        user.setGithub(userDTO.getGithub());
        user.setObjective(userDTO.getObjective());
        user.setHardSkills(userDTO.getHardSkills());
        user.setSoftSkills(userDTO.getSoftSkills());
        user.setLanguages(userDTO.getLanguages());

        mapToEntityListsForUpdate(user, userDTO);

        User updatedUser = userRepository.save(user);
        return mapToDTO(updatedUser);
    }

    // Atualização mantendo relação com user
    private void mapToEntityListsForUpdate(User user, UserDTO dto) {

        if (dto.getExperiences() != null) {
            List<Experience> experiences = dto.getExperiences().stream()
                    .map(exp -> {
                        Experience e = Experience.builder()
                                .id(exp.getId())
                                .companyName(exp.getCompanyName())
                                .position(exp.getPosition())
                                .period(exp.getPeriod())
                                .description(exp.getDescription())
                                .achievements(exp.getAchievements())
                                .build();

                        e.setUser(user);

                        return e;
                    })
                    .toList();

            user.setExperiences(experiences);
        }

        if (dto.getEducation() != null) {
            List<Education> education = dto.getEducation().stream()
                    .map(ed -> {
                        Education e = Education.builder()
                                .id(ed.getId())
                                .courseName(ed.getCourseName())
                                .institution(ed.getInstitution())
                                .startYear(ed.getStartYear())
                                .endYear(ed.getEndYear())
                                .build();

                        e.setUser(user);

                        return e;
                    })
                    .toList();

            user.setEducation(education);
        }

        if (dto.getCourses() != null) {
            List<Course> courses = dto.getCourses().stream()
                    .map(c -> {
                        Course e = Course.builder()
                                .id(c.getId())
                                .courseName(c.getCourseName())
                                .institution(c.getInstitution())
                                .year(c.getYear())
                                .build();

                        e.setUser(user);

                        return e;
                    })
                    .toList();

            user.setCourses(courses);
        }
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
