package com.example.courtathome.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.courtathome.dto.ResponseDto;
import com.example.courtathome.exception.CaseNotfoundException;
import com.example.courtathome.exception.LoginFailedException;
import com.example.courtathome.exception.PasswordWrongException;
import com.example.courtathome.exception.SomethingWentWrongException;
import com.example.courtathome.exception.UserNotPresentException;
import com.example.courtathome.exception.VehicleNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<ResponseDto> login(LoginFailedException exception) {
		return ResponseEntity.badRequest().body(new ResponseDto(true, exception.getMessage(), null));

	}

	@ExceptionHandler(CaseNotfoundException.class)
	public ResponseEntity<ResponseDto> noCase(CaseNotfoundException exception) {
		return ResponseEntity.badRequest().body(new ResponseDto(true, exception.getMessage(), null));

	}

	@ExceptionHandler(VehicleNotFoundException.class)
	public ResponseEntity<ResponseDto> vehicle(VehicleNotFoundException exception) {
		return ResponseEntity.badRequest().body(new ResponseDto(true, exception.getMessage(), null));

	}

	@ExceptionHandler(PasswordWrongException.class)
	public ResponseEntity<ResponseDto> update(PasswordWrongException exception) {
		return ResponseEntity.badRequest().body(new ResponseDto(true, exception.getMessage(), null));

	}

	@ExceptionHandler(UserNotPresentException.class)
	public ResponseEntity<ResponseDto> updateUser(UserNotPresentException exception) {
		return ResponseEntity.badRequest().body(new ResponseDto(true, exception.getMessage(), null));

	}

	@ExceptionHandler(SomethingWentWrongException.class)
	public ResponseEntity<ResponseDto> getPayment(SomethingWentWrongException exception) {
		return ResponseEntity.badRequest().body(new ResponseDto(true, exception.getMessage(), null));

	}

}
