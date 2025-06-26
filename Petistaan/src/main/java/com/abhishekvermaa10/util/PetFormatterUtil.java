package com.abhishekvermaa10.util;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.abhishekvermaa10.dto.DomesticPetDTO;

public class PetFormatterUtil {

	public static void applyFormattedBirthDate(DomesticPetDTO dto, Locale locale) {
		if (dto.getBirthDate() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);
			String formattedDate = dto.getBirthDate().format(formatter);
			dto.setFormattedBirthDate(formattedDate);
		}
	}
}
