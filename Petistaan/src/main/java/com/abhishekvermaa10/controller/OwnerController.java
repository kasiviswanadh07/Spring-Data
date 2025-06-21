package com.abhishekvermaa10.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

	private final OwnerService ownerService;

	@PostMapping("/save-Owner")
	public ResponseEntity<OwnerIdDTO> createOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
		OwnerIdDTO ownerIdDTO = ownerService.saveOwnerJson(ownerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerIdDTO);
	}

	@PostMapping("/find-Owner")
	public ResponseEntity<OwnerDTO> findOwnerById(@RequestBody OwnerIdDTO OwnerIdDTO) throws OwnerNotFoundException {
		OwnerDTO ownerDTO = ownerService.findOwnerByIdJson(OwnerIdDTO.getId());
		return ResponseEntity.status(HttpStatus.FOUND).body(ownerDTO);
	}

	@PostMapping("/update-pet")
	public void updatePetDetails(@RequestBody  Map<String, Object> requestBody ) throws OwnerNotFoundException {
		int id=(Integer) requestBody.get("ownerId");
		String petname=(String) requestBody.get("petName");
		 ownerService.updatePetDetails(id,petname);
	}
	@PostMapping("/delete-owner")
	public void deleteOwnerDetails(@RequestBody @Valid DeleteOwnerDTO deleteOwnerId) throws OwnerNotFoundException {
		 ownerService.deleteOwner(deleteOwnerId.getOwnerId());
	}
	@GetMapping("/fetch-allOwners")
	public  ResponseEntity<List<OwnerDTO>> fetchOwnersDetails()  {
		List<OwnerDTO>owners=ownerService.findAllOwners();
		return ResponseEntity.ok(owners);
	}
	@GetMapping("/owner-petInfo")
	public  ResponseEntity<Page<OwnerPetInfoDTO>> fetchOwnersDetails(Pageable pageable)  {
		Page<OwnerPetInfoDTO> page =ownerService.findIdAndFirstNameAndLastNameAndPetNameOfPaginatedOwnersJson(pageable);
		return ResponseEntity.ok(page);
	}
}
