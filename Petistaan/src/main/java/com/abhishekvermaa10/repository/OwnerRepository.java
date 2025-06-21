package com.abhishekvermaa10.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhishekvermaa10.dto.OwnerPetInfoDTO;
import com.abhishekvermaa10.entity.Owner;

/** 
 * @author abhishekvermaa10
 */
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

	@Query(value = "SELECT o.id AS id, o.first_name AS firstName, o.last_name AS lastName, p.name AS petName FROM owner_table o JOIN pet_table p ON o.pet_id = p.id",
		       countQuery = "SELECT count(*) FROM owner_table o JOIN pet_table p ON o.pet_id = p.id",
		       nativeQuery = true)
	Page<OwnerPetInfoDTO> findIdAndFirstNameAndLastNameAndPetNameJson(Pageable pageable);
	
	@Query("SELECT o.id, o.firstName, o.lastName, o.pet.name FROM Owner o JOIN o.pet")
	List<Object[]> findIdAndFirstNameAndLastNameAndPetName(Pageable pageable);

}
