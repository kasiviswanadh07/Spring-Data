package com.abhishekvermaa10.dto;

import java.time.LocalDate;

import com.abhishekvermaa10.enums.Gender;
import com.abhishekvermaa10.enums.PetType;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhishekvermaa10
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Setter
@Getter
@Schema(description = "Base class for all pet types")
public class DomesticPetDTO extends PetDTO {

	private LocalDate birthDate;

	@Builder
	public DomesticPetDTO(int id, String name, Gender gender, PetType type, OwnerDTO ownerDTO, LocalDate birthDate) {
		super(id, name, gender, type, ownerDTO);
		this.birthDate = birthDate;
	}
	@JsonProperty(access = JsonProperty.Access.READ_ONLY) 
    private String formattedBirthDate;

	public void setFormattedBirthDate(String formattedBirthDate) {
		this.formattedBirthDate = formattedBirthDate;
	}

	public String getFormattedBirthDate() {
		return formattedBirthDate;
	}

}
