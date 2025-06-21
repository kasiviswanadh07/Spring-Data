package com.abhishekvermaa10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPetInfoDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String petName;
}