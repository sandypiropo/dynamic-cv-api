package com.dynamiccv.service;

import com.dynamiccv.dto.ExperienceUserViewDTO;
import com.dynamiccv.dto.UserDTO;
import com.dynamiccv.model.User;
import com.dynamiccv.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        User savedUser = userRepository.save(user);
        System.out.println("ID gerado: " + savedUser.getId());
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
        // TODO: mapear experiences, education, courses
        user.setLanguages(dto.getLanguages());
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

        dto.setExperiences(
                entity.getExperiences()
                        .stream()
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
        // TODO: mapear experiences, education, courses
        user.setLanguages(userDTO.getLanguages());

        User updatedUser = userRepository.save(user);
        return mapToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

}
