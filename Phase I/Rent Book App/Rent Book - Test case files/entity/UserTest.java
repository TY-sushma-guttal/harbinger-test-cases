package com.example.rentbook.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.rentbook.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class UserTest {
	
	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"userId\":1,\"userName\":\"userName\"}";
	
	/**
	 * This is the test for serializing User entity
	 * @throws Exception
	 */
	@Test
	void serializationTest() throws Exception {
		User user = new User(1, "userName");
		System.err.println(mapper.writeValueAsString(user));
		String expected = mapper.writeValueAsString(mapper.readValue(json, User.class));
		assertEquals(mapper.writeValueAsString(user), expected);
	}// end of serializationTest method
	
	
	/**
	 * This is the test case for de-serializing User entity
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		User user = mapper.readValue(json, User.class);
		assertEquals(1, user.getUserId());
	}// end of deSerializeTest method
}
