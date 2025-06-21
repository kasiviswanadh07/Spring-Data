package com.abhishekvermaa10.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.AverageAgeDTO;
import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.exception.PetNotFoundException;
import com.abhishekvermaa10.service.PetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

	private final PetService petService;

	@PostMapping("/find-pet")
	public ResponseEntity<PetDTO> findPet(@RequestBody Map<String, Integer> requestBody) throws PetNotFoundException {
		Integer id = requestBody.get("id");
		return ResponseEntity.ok(petService.findPet(id));
	}

	@GetMapping("/average-age")
	public ResponseEntity<AverageAgeDTO> findAverageAgeOfPet() throws PetNotFoundException {

		return ResponseEntity.ok(petService.findAverageAgeOfPetJson());
	}
}
