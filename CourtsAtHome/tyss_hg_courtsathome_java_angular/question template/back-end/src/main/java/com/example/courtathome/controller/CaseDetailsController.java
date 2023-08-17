package com.example.courtathome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.courtathome.constant.Constant;
import com.example.courtathome.dto.CaseDetailsDto;
import com.example.courtathome.dto.ResponseDto;
import com.example.courtathome.service.CaseDetailsService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@RestController
@OpenAPIDefinition(info = @Info(title = "COURT AT HOME", description = "FIND YOUR ACTIVE CASES AND CLOSE IT", version = "V1"))
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*")
public class CaseDetailsController {

	@Autowired
	private CaseDetailsService caseDetailsService;

	@PostMapping("/create")
	@Operation(summary = "OPTIONAL API TO SAVE---REGISTER NEW CASE API ")
	public ResponseEntity<ResponseDto> registerCaseDetails(@RequestBody CaseDetailsDto caseDetailsDto) {
		log.info(Constant.CASEINFO_REGISTER_CONTROLLER);
		return ResponseEntity.ok(new ResponseDto(false, Constant.CASEINFO_ADD_SUCCESS,
				caseDetailsService.registerCaseDetails(caseDetailsDto)));
	}

	/*
	 * COMPLETE THE LOGIC OF GET CASE DETAILS API BELOW
	 */
	@GetMapping("/getCaseDetails")
	@Operation(summary = "FIND YOUR CASE STATUS ( Active/Closed ) API ")
	public ResponseEntity<ResponseDto> getCaseDetails(@RequestParam(required = false) String mobileNo,
			@RequestParam(required = false) String vehicleNo) {

		return null;

	}

	/*
	 * COMPLETE THE LOGIC OF UPDATE CASE DETAILS API BELOW
	 */
	@PutMapping("/updateCaseStatus")
	@Operation(summary = "CLICK ON PAY TO CLOSE YOUR ACTIVE CASE API")
	public ResponseEntity<ResponseDto> updateCaseDetails(@RequestBody CaseDetailsDto caseDetailsDto) {

		return null;

	}

}
