package com.abhishekvermaa10.exception;

import com.abhishekvermaa10.dto.ErrorDTO;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidateExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		String combinedMessage = String.join("; ", messages);

		ErrorDTO errorDTO = new ErrorDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation Error",
				combinedMessage);

		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<ErrorDTO> handleInvalidFormat(InvalidFormatException ex) {
		String fieldName = !ex.getPath().isEmpty() ? ex.getPath().get(0).getFieldName() : "Unknown";
		String targetType = ex.getTargetType().getSimpleName();
		String message;

		if ("Gender".equals(targetType)) {
			message = "Gender must be either M or F";
		} else if ("PetType".equals(targetType)) {
			message = "Pet type must be one of: BIRD, CAT, DOG, FISH, RABBIT";
		} else {
			message = "Invalid value for field: " + fieldName;
		}

		ErrorDTO errorDTO = new ErrorDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Invalid Format",
				message);

		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidTypeIdException.class)
	public ResponseEntity<ErrorDTO> handleInvalidTypeId(InvalidTypeIdException ex) {
		ErrorDTO errorDTO = new ErrorDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Invalid Type",
				"Pet type must be either 'Wild' or 'Domestic'");

		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorDTO> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		String rawMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
		String message;

		if (rawMessage != null && rawMessage.contains("Duplicate entry") && rawMessage.contains("gmail.com")) {
			message = "Email already exists or is already used";
		} else if (rawMessage != null && rawMessage.contains("Duplicate entry")) {
			message = "Mobile number already exists or is already used";
		} else if (rawMessage != null
				&& (rawMessage.contains("place_of_birth") || rawMessage.contains("date_of_birth"))) {
			message = "For Domestic pet: dateOfBirth is required in dd-MM-yyyy format. For Wild pet: placeOfBirth is required. Details: "
					+ rawMessage;
		} else {
			message = "Data integrity violation: " + rawMessage;
		}

		ErrorDTO errorDTO = new ErrorDTO(LocalDateTime.now(), HttpStatus.CONFLICT.value(), "Data Integrity Violation",
				message);

		return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(OwnerNotFoundException.class)
	public ResponseEntity<ErrorDTO> handleOwnerNotFoundException(OwnerNotFoundException ex) {
	    ErrorDTO errorDTO = new ErrorDTO(
	        LocalDateTime.now(),
	        HttpStatus.NOT_FOUND.value(),
	        HttpStatus.NOT_FOUND.getReasonPhrase(),
	        ex.getMessage()
	       
	    );
	    return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
	}

}
