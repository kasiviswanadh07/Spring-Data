package com.abhishekvermaa10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.OwnerIdDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.service.OwnerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

	private final OwnerService ownerService;

	@PostMapping("/saveOwner")
	public ResponseEntity<OwnerIdDTO> createOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
		OwnerIdDTO ownerIdDTO = ownerService.saveOwnerJson(ownerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerIdDTO);
	}

	@PostMapping("/findOwner")
	public ResponseEntity<OwnerDTO> findOwnerById(@RequestBody OwnerIdDTO OwnerIdDTO) throws OwnerNotFoundException {
		OwnerDTO ownerDTO = ownerService.findOwnerByIdJson(OwnerIdDTO.getId());
		return ResponseEntity.status(HttpStatus.FOUND).body(ownerDTO);
	}
}
