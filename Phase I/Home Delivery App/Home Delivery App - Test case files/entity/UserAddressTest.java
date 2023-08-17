package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserAddress;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class UserAddressTest {

	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"addressId\":1,\"address\":\"address\",\"addressType\":\"type\"}";

	
	/**
	 * This is the test case for serializing the UserAddress entity
	 * @throws Exception
	 */
	@Test
	void serializationTest() throws Exception {
		UserAddress userAddress = new UserAddress(1l, "address", new User(), "type");
		String expected = mapper.writeValueAsString(mapper.readValue(json, UserAddress.class));
		assertEquals(mapper.writeValueAsString(userAddress), expected);

	}

	/**
	 * This is the test case for de-serializing the UserAddress entity
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		UserAddress userAddress = mapper.readValue(json, UserAddress.class);
		assertEquals("address", userAddress.getAddress());
	}

}
