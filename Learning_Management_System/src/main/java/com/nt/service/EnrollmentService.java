package com.nt.service;



import java.util.List;

import com.nt.dto.EnrollmentProgressDTO;
import com.nt.entity.Enrollment;

public interface EnrollmentService {
	Enrollment saveEnrollment(Enrollment enrollment);
    List<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(Long id);
    void deleteEnrollment(Long id);
 // Update progress and return DTO
    EnrollmentProgressDTO updateProgress(Long id, double completionPercentage);
 // Get progress for all enrollments
    List<EnrollmentProgressDTO> getAllProgress();



    
}


