package com.tyss.courtathome.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.courtathome.entity.VehicleDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class VehicleDetailsTest {

	ObjectMapper objectMapper = new ObjectMapper();

	String json = "{\"vehicleNo\":\"KA-01-SS-7894\",\"vehicleType\":\"Two-Wheeler\",\"userDetails\":null,\"caseList\":null}";

	@Test
	void serializeTestForVehicleInfo() throws JsonProcessingException {
		String writeValueAsString = objectMapper.writeValueAsString(objectMapper.readValue(json, VehicleDetails.class));
		assertThat(writeValueAsString).isEqualTo(json);
	}

	@Test
	void deserializeTestForCaseInfo() throws JsonMappingException, JsonProcessingException {
		VehicleDetails readValue = objectMapper.readValue(json, VehicleDetails.class);
		assertEquals("KA-01-SS-7894", readValue.getVehicleNo());
	}
}
