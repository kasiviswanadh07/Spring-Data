package com.abhishekvermaa10.service;

import java.util.List;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.OwnerIdDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;

/**
 * @author abhishekvermaa10
 */
public interface OwnerService {

	OwnerIdDTO saveOwnerJson(OwnerDTO ownerDTO);

	void saveOwnerCmd(OwnerDTO ownerDTO);

	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

	OwnerDTO findOwnerByIdJson(int id) throws OwnerNotFoundException;

	void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

	void deleteOwner(int ownerId) throws OwnerNotFoundException;

	List<OwnerDTO> findAllOwners();

	List<Object[]> findIdAndFirstNameAndLastNameAndPetNameOfPaginatedOwners(int pageNumber, int numberOfRecordsPerPage);

}
