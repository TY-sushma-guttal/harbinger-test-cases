package com.example.courtathome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.courtathome.constant.Constant;
import com.example.courtathome.dto.ResponseDto;
import com.example.courtathome.dto.UserDetailsDto;
import com.example.courtathome.service.UserDetailsService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@RestController
@OpenAPIDefinition(info = @Info(title = "COURT AT HOME", description = "FIND YOUR ACTIVE CASES AND CLOSE IT", version = "V1"))
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*")
public class UserDetailsController {

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/register")
	@Operation(summary = "REGISTER USER AND VEHICLE INFORMATION API ")
	public ResponseEntity<ResponseDto> register(@RequestBody UserDetailsDto userDetailsDto) {
		log.info(Constant.USER_REGISTER_CONTROLLER);
		return ResponseEntity.ok(
				new ResponseDto(false, Constant.USER_REGISTER_SUCCESS, userDetailsService.register(userDetailsDto)));
	}

	@GetMapping("/login")
	@Operation(summary = "LOGIN USER API ")
	public ResponseEntity<ResponseDto> login(@RequestParam(required = false) String vehcileNo,
			@RequestParam(required = false) String mobileNo, @RequestParam String password) {
		log.info(Constant.USER_LOGIN_CONTROLLER);
		return ResponseEntity.ok(new ResponseDto(false, Constant.USER_LOGIN_SUCCESS,
				userDetailsService.login(vehcileNo, mobileNo, password)));
	}

}
