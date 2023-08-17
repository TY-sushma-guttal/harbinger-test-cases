package com.tyss.courtathome.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.courtathome.entity.CaseDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class CaseDetailsTest {

	ObjectMapper objectMapper = new ObjectMapper();

	String json = "{\"caseId\":1,\"caseName\":\"OverSpeed\",\"datetime\":null,\"status\":\"Active\",\"fine\":1000.0,\"vehicleDetails\":null,\"userDetails\":null}";

	@Test
	void serializeTestForCaseDetails() throws JsonProcessingException {
		String writeValueAsString = objectMapper.writeValueAsString(objectMapper.readValue(json, CaseDetails.class));
		assertThat(writeValueAsString).isEqualTo(json);
	}

	@Test
	void deserializeTestForCaseInfo() throws JsonMappingException, JsonProcessingException {
		CaseDetails readValue = objectMapper.readValue(json, CaseDetails.class);
		assertEquals("OverSpeed", readValue.getCaseName());
	}

}
