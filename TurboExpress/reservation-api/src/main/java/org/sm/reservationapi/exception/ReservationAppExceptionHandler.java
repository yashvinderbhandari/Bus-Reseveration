package org.sm.reservationapi.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sm.reservationapi.dto.ResponseStrcture;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReservationAppExceptionHandler {
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ResponseStrcture<String>> handlerANFE(AdminNotFoundException e) {
		ResponseStrcture<String> strcture = new ResponseStrcture<>();
		strcture.setData("Admin Not Found");
		strcture.setMessage(e.getMessage());
		strcture.setStatus(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(strcture);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStrcture<String>> handlerUNFE(UserNotFoundException e) {
		ResponseStrcture<String> strcture = new ResponseStrcture<>();
		strcture.setData("User Not Found");
		strcture.setMessage(e.getMessage());
		strcture.setStatus(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(strcture);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handlerMANVE(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		List<ObjectError> listErrors = e.getBindingResult().getAllErrors();
		for (ObjectError err : listErrors) {
			String field = ((FieldError) err).getField();
			String message = err.getDefaultMessage();
			errors.put(field, message);
		}
		return errors;
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ResponseStrcture<String>> illegalStateHandle(IllegalStateException exception) {
		ResponseStrcture<String> strcture = new ResponseStrcture<>();
		strcture.setData("Can't sign-in");
		strcture.setMessage(exception.getMessage());
		strcture.setStatus(HttpStatus.UNAUTHORIZED.value());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(strcture);
	}
}
