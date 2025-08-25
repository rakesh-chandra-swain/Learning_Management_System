package com.nt.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.EnrollmentProgressDTO;
import com.nt.entity.Enrollment;
import com.nt.entity.ProgressStatus;
import com.nt.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
    }

    @Override
    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new RuntimeException("Enrollment not found with id: " + id);
        }
        enrollmentRepository.deleteById(id);
    }
    

    @Override
    public EnrollmentProgressDTO updateProgress(Long id, double completionPercentage) {
        Enrollment enrollment = getEnrollmentById(id);

        enrollment.setCompletionPercentage(completionPercentage);

        // Update progress status
        if (completionPercentage == 0) {
            enrollment.setProgressStatus(ProgressStatus.NOT_STARTED);
        } else if (completionPercentage > 0 && completionPercentage < 100) {
            enrollment.setProgressStatus(ProgressStatus.IN_PROGRESS);
        } else if (completionPercentage >= 100) {
            enrollment.setProgressStatus(ProgressStatus.COMPLETED);
        }

        enrollment.setLastUpdated(LocalDateTime.now());
        enrollmentRepository.save(enrollment);

        // Return DTO
        return new EnrollmentProgressDTO(
                enrollment.getProgressStatus(),
                enrollment.getCompletionPercentage(),
                enrollment.getLastUpdated()
        );
    }

    @Override
    public List<EnrollmentProgressDTO> getAllProgress() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        return enrollments.stream()
                .map(enrollment -> new EnrollmentProgressDTO(
                        enrollment.getProgressStatus(),
                        enrollment.getCompletionPercentage(),
                        enrollment.getLastUpdated()
                ))
                .toList();
    }

   

 
}


