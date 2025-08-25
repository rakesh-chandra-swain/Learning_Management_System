package com.nt.entity;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name = "enrollment_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String modeOfTraining; // Online / Offline
    private String state;

    @ManyToOne
    @JoinColumn(name = "course_id")  // foreign key
    @JsonIgnore
    private Course course;
    
    // ---------- Progress Tracking ----------
    @Enumerated(EnumType.STRING)
    private ProgressStatus progressStatus = ProgressStatus.NOT_STARTED;

    private double completionPercentage = 0.0;  // 0 to 100%

    private LocalDateTime lastUpdated;  // track last update time


   
}
