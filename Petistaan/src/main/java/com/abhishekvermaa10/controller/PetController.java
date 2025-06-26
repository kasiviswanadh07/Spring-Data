package com.abhishekvermaa10.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.AverageAgeDTO;
import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.exception.PetNotFoundException;
import com.abhishekvermaa10.service.PetService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

	private final PetService petService;

	@PostMapping("/find-pet")
	@Operation(summary = "Find pet by ID", description = "Fetches pet details based on the provided pet ID. Throws an error if the pet is not found.")
	public ResponseEntity<PetDTO> findPet(@RequestBody Map<String, Integer> requestBody) throws PetNotFoundException {

		Integer id = requestBody.get("id");
		return ResponseEntity.ok(petService.findPet(id));
	}

	@GetMapping("/average-age")
	@Operation(summary = "Find average pet age", description = "Returns the average age of all registered pets in the system.")
	public ResponseEntity<AverageAgeDTO> findAverageAgeOfPet() throws PetNotFoundException {

		return ResponseEntity.ok(petService.findAverageAgeOfPetJson());
	}
}
