package com.example.courtathome.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.courtathome.controller.CaseDetailsController;
import com.example.courtathome.dto.CaseDetailsDto;
import com.example.courtathome.dto.ResponseDto;
import com.example.courtathome.service.CaseDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class CaseDetailsControllerTest {

	private MockMvc mockMvc;

	@Mock
	private CaseDetailsService caseDetailsService;

	@InjectMocks
	private CaseDetailsController controller;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

//	CaseInfoDto caseInfoDto = new CaseInfoDto(1, "OverSpeed", null, null, "Pay Now", "KA-01-SS-8888");
	CaseDetailsDto caseDetailsDto = new CaseDetailsDto(1, "OverSpeed", null, "Active", 1000.00, "KA-01-SS-8888", null);
	List<CaseDetailsDto> caseDetailsDtos = List.of(caseDetailsDto);

	@Test 
	void getCases_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(caseDetailsService.getCaseDetails(anyString(), anyString())).thenReturn(caseDetailsDtos);
		String contentAsString = mockMvc
				.perform(get("/api/v1/get").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(caseDetailsDto)).param("vehicleNo", "KA-01-SS-8888"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Get All CaseInfo Status Successfully", readValue.getMsg());
	}

	@Test
	void updateCaseDetails_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(caseDetailsService.updateCaseDetails(any())).thenReturn(caseDetailsDto);
		String contentAsString = mockMvc
				.perform(put("/api/v1/updateCaseStatus").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(caseDetailsDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Status Updated to Close  Successfully after Successfull Payment", readValue.getMsg());
	}
}
