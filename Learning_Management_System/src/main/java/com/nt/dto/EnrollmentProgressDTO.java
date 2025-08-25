package com.nt.dto;

import java.time.LocalDateTime;

import com.nt.entity.ProgressStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentProgressDTO {
	
	private ProgressStatus progressStatus;
    private double completionPercentage;
    private LocalDateTime lastUpdated;

}
