package com.abhishekvermaa10.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class UpdatePetDTO {
	@NotNull(message = "OwnerId cannot be null")
	private int ownerId;
	@NotNull(message = "petName cannot be null")
	private String petName;

}
