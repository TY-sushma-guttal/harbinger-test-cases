package com.tyss.courtathome.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.courtathome.entity.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class UserDetailsTest {

	ObjectMapper objectMapper = new ObjectMapper();

	String json = "{\"userId\":1,\"name\":\"Sumeet\",\"mobileNo\":\"987456321\",\"email\":\"s@gmail.com\",\"address\":\"Bengaluru\",\"password\":\"123\",\"vehicleDetails\":null,\"caseDetails\":null}";

	@Test
	void UserInfoSerializeTest() throws JsonProcessingException {
		String writeValueAsString = objectMapper.writeValueAsString(objectMapper.readValue(json, UserDetails.class));
		assertThat(writeValueAsString).isEqualTo(json);
	}

	@Test
	void deserializeTestForUserInfo() throws JsonMappingException, JsonProcessingException {
		UserDetails readValue = objectMapper.readValue(json, UserDetails.class);
		assertEquals("Sumeet", readValue.getName());
	}
}
