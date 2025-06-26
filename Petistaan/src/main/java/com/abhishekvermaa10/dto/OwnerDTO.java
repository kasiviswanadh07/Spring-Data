package com.abhishekvermaa10.dto;

import com.abhishekvermaa10.enums.Gender;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO representing an Owner
 */
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class OwnerDTO {

	@EqualsAndHashCode.Include
	private int id;

	@NotBlank(message = "{owner.first.name.required}")
	@Size(min = 2, max = 30, message = "{owner.first.name.length}")
	private String firstName;

	@NotBlank(message = "{owner.last.name.required}")
	@Size(max = 30, message = "{owner.last.name.length}")
	private String lastName;

	@NotNull(message = "{owner.gender.required}")
	private Gender gender;

	@NotBlank(message = "{owner.city.required}")
	@Size(max = 30, message = "{owner.city.length}")
	private String city;

	@NotBlank(message = "{owner.state.required}")
	@Size(max = 30, message = "{owner.state.length}")
	private String state;

	@EqualsAndHashCode.Include
	@NotBlank(message = "{owner.mobile.number.required}")
	@Pattern(regexp = "\\d{10}", message = "{owner.mobile.number.length}")
	private String mobileNumber;

	@EqualsAndHashCode.Include
	@NotBlank(message = "{owner.email.required}")
	@Email(message = "{owner.email.invalid}")
	@Pattern(
	    regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z]+\\.[A-Za-z]{2,6}$",
	    message = "{owner.email.invalid}"
	)

	private String emailId;

	@Valid
	@NotNull(message = "{owner.pet.required}")
	private PetDTO petDTO;
}
