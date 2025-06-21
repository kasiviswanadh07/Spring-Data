package com.abhishekvermaa10.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteOwnerDTO {
	
	@NotNull(message = "OwnerId cannot be null")
	private int ownerId;
}
