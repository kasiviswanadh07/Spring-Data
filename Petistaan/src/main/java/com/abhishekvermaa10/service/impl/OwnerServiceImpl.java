package com.abhishekvermaa10.service.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.abhishekvermaa10.dto.DomesticPetDTO;
import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.OwnerIdDTO;
import com.abhishekvermaa10.dto.OwnerPetInfoDTO;
import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.entity.Owner;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.repository.OwnerRepository;
import com.abhishekvermaa10.service.OwnerService;
import com.abhishekvermaa10.util.OwnerMapper;
import com.abhishekvermaa10.util.PetFormatterUtil;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {

	private final OwnerRepository ownerRepository;

	private final OwnerMapper ownerMapper;
	@Value("${owner.not.found}")
	private String ownerNotFound;

	@Override
	public OwnerIdDTO saveOwnerJson(OwnerDTO ownerDTO) {
		Owner owner = ownerMapper.ownerDTOToOwner(ownerDTO);
		Owner savedOwnerid = ownerRepository.save(owner);
		return new OwnerIdDTO(savedOwnerid.getId());
	}

	@Override
	public void saveOwnerCmd(OwnerDTO ownerDTO) {
		Owner owner = ownerMapper.ownerDTOToOwner(ownerDTO);
		ownerRepository.save(owner);
	}

	@Override
	public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
		return ownerRepository.findById(ownerId).map(ownerMapper::ownerToOwnerDTO)
				.orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
	}

	public OwnerDTO findOwnerByIdJson(int ownerId) throws OwnerNotFoundException {
		return ownerRepository.findById(ownerId).map(owner -> {
			 OwnerDTO ownerDTO = ownerMapper.ownerToOwnerDTO(owner);
			if (ownerDTO.getPetDTO() instanceof DomesticPetDTO domesticPetDTO) {
				Locale locale = LocaleContextHolder.getLocale();
				PetFormatterUtil.applyFormattedBirthDate(domesticPetDTO, locale);
			}
			return ownerDTO;
		}).orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findById(ownerId)
				.orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
		owner.getPet().setName(petName);
		ownerRepository.save(owner);
	}

	@Override
	public void deleteOwner(int ownerId) throws OwnerNotFoundException {
		boolean ownerExists = ownerRepository.existsById(ownerId);
		if (!ownerExists) {
			throw new OwnerNotFoundException(String.format(ownerNotFound, ownerId));
		} else {
			ownerRepository.deleteById(ownerId);
		}
	}

//	@Override
//	public List<OwnerDTO> findAllOwners() {
//		return ownerRepository.findAll().stream().map(ownerMapper::ownerToOwnerDTO).toList();
//
//	}
	@Override
	public List<OwnerDTO> findAllOwners() {
		Locale locale = LocaleContextHolder.getLocale();

		return ownerRepository.findAll().stream().map(ownerMapper::ownerToOwnerDTO).peek(ownerDTO -> {
			PetDTO petDTO = ownerDTO.getPetDTO();
			if (petDTO instanceof DomesticPetDTO domesticPetDTO) {
				PetFormatterUtil.applyFormattedBirthDate(domesticPetDTO, locale);
			}
		}).toList();
	}

	@Override
	public List<Object[]> findIdAndFirstNameAndLastNameAndPetNameOfPaginatedOwners(int pageNumber,
			int numberOfRecordsPerPage) {
		Pageable pageable = PageRequest.of(pageNumber, numberOfRecordsPerPage);
		return ownerRepository.findIdAndFirstNameAndLastNameAndPetName(pageable);
	}

	@Override
	public Page<OwnerPetInfoDTO> findIdAndFirstNameAndLastNameAndPetNameOfPaginatedOwnersJson(Pageable pageable) {
		return ownerRepository.findIdAndFirstNameAndLastNameAndPetNameJson(pageable);
	}

}
