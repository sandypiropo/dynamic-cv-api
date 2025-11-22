package com.dynamiccv.service;

import com.dynamiccv.dto.CourseDTO;
import com.dynamiccv.model.Course;
import com.dynamiccv.model.User;
import com.dynamiccv.repository.CourseRepository;
import com.dynamiccv.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public List<CourseDTO> getAllCourses(Long userId) {
        List<Course> courses;
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
            courses = user.getCourses();
        } else {
            courses = courseRepository.findAll();
        }
        return courses.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return mapToDTO(course);
    }

    public CourseDTO createCourse(Long userId, CourseDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Course course = mapToEntity(dto);
        course.setUser(user);

        Course saved = courseRepository.save(course);
        return mapToDTO(saved);
    }

    public CourseDTO updateCourse(Long id, Long userId, CourseDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        course.setCourseName(dto.getCourseName());
        course.setInstitution(dto.getInstitution());
        course.setYear(dto.getYear());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        course.setUser(user);

        Course updated = courseRepository.save(course);
        return mapToDTO(updated);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    private CourseDTO mapToDTO(Course entity) {
        return new CourseDTO(
                entity.getId(),
                entity.getCourseName(),
                entity.getInstitution(),
                entity.getYear()
        );
    }

    private Course mapToEntity(CourseDTO dto) {
        Course course = new Course();
        course.setCourseName(dto.getCourseName());
        course.setInstitution(dto.getInstitution());
        course.setYear(dto.getYear());
        return course;
    }
}
