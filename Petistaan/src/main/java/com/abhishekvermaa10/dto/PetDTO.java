package com.abhishekvermaa10.dto;

import com.abhishekvermaa10.enums.Gender;
import com.abhishekvermaa10.enums.PetType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhishekvermaa10
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME,
		  include = JsonTypeInfo.As.PROPERTY,
		  property = "@type"
		)
		@JsonSubTypes({
		  @JsonSubTypes.Type(value = DomesticPetDTO.class, name = "domestic"),
		  @JsonSubTypes.Type(value = WildPetDTO.class, name = "wild")
		})
public  class PetDTO {

	@EqualsAndHashCode.Include
	private int id;
	private String name;
	private Gender gender;
	private PetType type;
	private OwnerDTO ownerDTO;

}
