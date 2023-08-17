package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.demo.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

 class UserTest {
	
	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"userId\":1,\"userName\":\"name\",\"contactNo\":1,\"emailid\":\"email\",\"userAddressList\":[],\"userProductList\":[]}";	
	
	/**
	 * This is the test case for serializing the User entity
	 * @throws Exception
	 */
	@Test
	void serializationTest() throws Exception {
		User user = new User(1l, "name", 1l, "email", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		String expected = mapper.writeValueAsString(mapper.readValue(json, User.class));
		assertEquals(mapper.writeValueAsString(user), expected);
	}// end of serializationTest method

	/**
	 *  This is the test case for de-serializing the User entity
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		User user = mapper.readValue(json, User.class);
		assertEquals("email", user.getEmailid());
	}// end of deSerializeTest method

}
