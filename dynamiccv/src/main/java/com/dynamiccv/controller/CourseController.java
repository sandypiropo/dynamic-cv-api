package com.dynamiccv.controller;

import com.dynamiccv.dto.CourseDTO;
import com.dynamiccv.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses(@RequestParam(required = false) Long userId) {
        return courseService.getAllCourses(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO dto = courseService.getCourseById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(
            @RequestParam Long userId,
            @Valid @RequestBody CourseDTO dto) {
        CourseDTO created = courseService.createCourse(userId, dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable Long id,
            @RequestParam Long userId,
            @Valid @RequestBody CourseDTO dto) {
        CourseDTO updated = courseService.updateCourse(id, userId, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
