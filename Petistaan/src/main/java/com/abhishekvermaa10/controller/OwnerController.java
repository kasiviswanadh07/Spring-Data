package com.abhishekvermaa10.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.abhishekvermaa10.dto.DeleteOwnerDTO;
import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.OwnerIdDTO;
import com.abhishekvermaa10.dto.OwnerPetInfoDTO;
import com.abhishekvermaa10.dto.UpdatePetDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.service.OwnerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/owners")
@RequiredArgsConstructor
@Tag(name = "Owner Controller", description = "Manage owner registration, search, and updates")
public class OwnerController {

	private final OwnerService ownerService;

	@PostMapping("/save-Owner")
	@Operation(summary = "Register new owner", description = "Adds a new owner to the system along with their pet details. "
			+ "The request must contain owner information and embedded pet data. "
			+ "Pet type can be either 'Domestic' or 'Wild'.")
	public ResponseEntity<OwnerIdDTO> createOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
		OwnerIdDTO ownerIdDTO = ownerService.saveOwnerJson(ownerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerIdDTO);
	}

	@PostMapping("/find-Owner")
	@Operation(summary = "Get owner by ID", description = "Returns the owner details for a given ID")
	public ResponseEntity<OwnerDTO> findOwnerById(@RequestBody OwnerIdDTO OwnerIdDTO) throws OwnerNotFoundException {
		OwnerDTO ownerDTO = ownerService.findOwnerByIdJson(OwnerIdDTO.getId());
		return ResponseEntity.status(HttpStatus.FOUND).body(ownerDTO);
	}

	@PostMapping("/update-pet")
	@Operation(summary = "Update pet details", description = "Updates the details of a pet based on the provided owner ID and pet name. "
			+ "Use this endpoint to modify existing pet records for a specific owner.")
	public void updatePetDetails(@RequestBody Map<String, Object> requestBody) throws OwnerNotFoundException {
		int id = (Integer) requestBody.get("ownerId");
		String petname = (String) requestBody.get("petName");
		ownerService.updatePetDetails(id, petname);
	}

	@PostMapping("/delete-owner")
	@Operation(summary = "Delete owner details", description = "Deletes the owner and associated pet details from the system based on the provided owner ID. "
			+ "Ensure the owner ID exists before making the request to avoid errors.")
	public void deleteOwnerDetails(@RequestBody @Valid DeleteOwnerDTO deleteOwnerId) throws OwnerNotFoundException {
		ownerService.deleteOwner(deleteOwnerId.getOwnerId());
	}

	@GetMapping("/fetch-allOwners")
	@Operation(summary = "Fetch all owners", description = "Retrieves a list of all registered owners along with their associated pet details. "
			+ "This endpoint returns full owner profiles currently stored in the system.")
	public ResponseEntity<List<OwnerDTO>> fetchOwnersDetails() {
		List<OwnerDTO> owners = ownerService.findAllOwners();
		return ResponseEntity.ok(owners);
	}

	@GetMapping("/owner-petInfo")
	@Operation(summary = "Fetch paginated owner and pet info", description = "Retrieves a paginated list of owners with selected fields: owner ID, first name, last name, and pet name. "
			+ "Supports standard pagination parameters like page, size, and sort.")
	public ResponseEntity<Page<OwnerPetInfoDTO>> fetchOwnersDetails(Pageable pageable) {
		Page<OwnerPetInfoDTO> page = ownerService
				.findIdAndFirstNameAndLastNameAndPetNameOfPaginatedOwnersJson(pageable);
		return ResponseEntity.ok(page);
	}
}
