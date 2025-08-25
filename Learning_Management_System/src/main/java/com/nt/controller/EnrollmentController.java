package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.EnrollmentProgressDTO;
import com.nt.entity.Enrollment;
import com.nt.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Save Enrollment
    @PostMapping("/save")
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }

    // Get All Enrollments
    @GetMapping("/all")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // Get Enrollment by Id
    @GetMapping("/{id}")
    public Enrollment getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    // Delete Enrollment by Id
    @DeleteMapping("/{id}")
    public String deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return "Enrollment with ID " + id + " deleted successfully.";
    }
    
 // Update Progress by Enrollment ID
    @PutMapping("/{id}/progress")
    public EnrollmentProgressDTO updateProgress(
            @PathVariable Long id,
            @RequestParam double completionPercentage) {
        return enrollmentService.updateProgress(id, completionPercentage);
    }
    
    @GetMapping("/progress/all")
    public List<EnrollmentProgressDTO> getAllProgress(@RequestHeader("Authorization") String jwt) {
        return enrollmentService.getAllProgress();
    }




}
