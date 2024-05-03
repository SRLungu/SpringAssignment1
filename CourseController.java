package com.example.springassignment1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    // Retrieve all courses
    @GetMapping
    public List<courses> getAllCourses() {
        return courseRepository.findAll();
    }

    // Retrieve a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<courses> getCourseById(@PathVariable(value = "id") Long courseId)
            throws ResourceNotFoundException {
        courses course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));
        return ResponseEntity.ok().body(course);
    }

    // Create a new course
    @PostMapping
    public ResponseEntity<?> createCourse(@Valid @RequestBody courses course, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("errors", result.getAllErrors());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        return new ResponseEntity<>(courseRepository.save(course), HttpStatus.CREATED);
    }

    // Update a course by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable(value = "id") Long courseId,
                                          @Valid @RequestBody courses courseDetails, BindingResult result)
            throws ResourceNotFoundException {
        if (result.hasErrors()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("errors", result.getAllErrors());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        courses course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());
        final courses updatedCourse = courseRepository.save(course);
        return ResponseEntity.ok(updatedCourse);
    }

    // Delete a course by ID
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") Long courseId)
            throws ResourceNotFoundException {
        courses course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + courseId));

        courseRepository.delete(course);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
