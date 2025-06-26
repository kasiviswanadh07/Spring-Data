package com.abhishekvermaa10.dto;

import com.abhishekvermaa10.enums.Gender;
import com.abhishekvermaa10.enums.PetType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO representing a Pet
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({ 
    @JsonSubTypes.Type(value = DomesticPetDTO.class, name = "Domestic"),
    @JsonSubTypes.Type(value = WildPetDTO.class, name = "Wild") 
})
public class PetDTO {

    @EqualsAndHashCode.Include
    private int id;

    @NotBlank(message = "{pet.name.required}")
    @Size(max = 30, message = "{pet.name.length}")
    private String name;

    @NotNull(message = "{pet.gender.required}")
    private Gender gender;

    @NotNull(message = "{pet.type.required}")
    private PetType type;

    private OwnerDTO ownerDTO;
}
