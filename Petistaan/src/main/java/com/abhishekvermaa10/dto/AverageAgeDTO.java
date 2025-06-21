package com.abhishekvermaa10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AverageAgeDTO {
	 private double averageAge = 0.0; 

	public AverageAgeDTO(double averageAge) {
		this.averageAge = averageAge;
	}
}
