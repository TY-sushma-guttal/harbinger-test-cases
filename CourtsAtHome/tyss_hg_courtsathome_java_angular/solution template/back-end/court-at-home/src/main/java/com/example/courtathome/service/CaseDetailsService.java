package com.example.courtathome.service;

import java.util.List;

import com.example.courtathome.dto.CaseDetailsDto;

public interface CaseDetailsService {

	CaseDetailsDto registerCaseDetails(CaseDetailsDto caseDetailsDto);

	List<CaseDetailsDto> getCaseDetails(String mobileNo, String vehicleNo);

	CaseDetailsDto updateCaseDetails(CaseDetailsDto caseDetailsDto);

}
