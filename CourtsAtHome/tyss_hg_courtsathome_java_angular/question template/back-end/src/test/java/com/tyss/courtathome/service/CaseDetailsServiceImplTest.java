package com.tyss.courtathome.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.courtathome.dto.CaseDetailsDto;
import com.example.courtathome.entity.CaseDetails;
import com.example.courtathome.entity.UserDetails;
import com.example.courtathome.entity.VehicleDetails;
import com.example.courtathome.exception.CaseNotfoundException;
import com.example.courtathome.exception.SomethingWentWrongException;
import com.example.courtathome.reposiotry.CaseDetailsRepository;
import com.example.courtathome.reposiotry.VehicleDetailsRepository;
import com.example.courtathome.service.CaseDetailsServiceImpl;

@SpringBootTest

class CaseDetailsServiceImplTest {

	@InjectMocks
	private CaseDetailsServiceImpl caseDetailsServiceImpl;

	@Mock
	private CaseDetailsRepository caseDetailsRepository;

	@Mock
	private VehicleDetailsRepository vehicleDetailsRepository;

	UserDetails userDetails = new UserDetails(1, "Sumeet", "987456321", null, "123", null, null, null);
	VehicleDetails vehicleDetails = new VehicleDetails("KA-01-SS-7894", "Two-Wheeler", userDetails, null);
	CaseDetails caseDetails = new CaseDetails(1, "OverSpeed", null, "Active", 1000.00, vehicleDetails, userDetails);
	List<CaseDetails> list = List.of(caseDetails);
	CaseDetailsDto caseDetailsDto = new CaseDetailsDto(1, "OverSpeed", null, "Active", 1000.00, "KA-01-SS-7894", null);

	@Test
	void getCases_success() {
		when(caseDetailsRepository.findByVehicleDetailsVehicleNoOrUserDetailsMobileNo(anyString(), anyString()))
				.thenReturn(list);
		List<CaseDetailsDto> cases = caseDetailsServiceImpl.getCaseDetails("KA-01-SS-7894", "987456321");
		assertEquals("Active", cases.get(0).getStatus());
	}

	@Test
	void getCases_fail() {
		List<CaseDetails> list = new ArrayList<>();
		when(caseDetailsRepository.findByVehicleDetailsVehicleNoOrUserDetailsMobileNo(anyString(), anyString()))
				.thenReturn(list);
		assertThatThrownBy(() -> caseDetailsServiceImpl.getCaseDetails("KA-01-SS-7894", "987456321"))
				.isInstanceOf(CaseNotfoundException.class);
	}

	@Test
	void updateCaseDetails_success() {
		Optional<CaseDetails> optional = Optional.of(caseDetails);
		when(caseDetailsRepository.findById(anyInt())).thenReturn(optional);
		optional.get().setStatus("Closed");
		when(caseDetailsRepository.save(any())).thenReturn(caseDetails);
		CaseDetailsDto updateCaseDetails = caseDetailsServiceImpl.updateCaseDetails(caseDetailsDto);
		assertEquals("OverSpeed", updateCaseDetails.getCaseName());
	}

	@Test
	void updateCaseDetails_fail() {
		Optional<CaseDetails> optional = Optional.empty();
		when(caseDetailsRepository.findById(anyInt())).thenReturn(optional);
		assertThatThrownBy(() -> caseDetailsServiceImpl.updateCaseDetails(caseDetailsDto))
				.isInstanceOf(SomethingWentWrongException.class);
	}
}
