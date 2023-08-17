package com.example.rentbook.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.rentbook.pojo.Admin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class AdminTest {

	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"adminId\":1,\"adminName\":\"adminName\",\"amount\":2000}";
	
	/**
	 * This is the test method for serializing Admin entity
	 * @throws Exception
	 */
	@Test
	void serializationTest() throws Exception {
		Admin admin = new Admin(1, "adminName", 2000);
		String expected = mapper.writeValueAsString(mapper.readValue(json, Admin.class));
		assertEquals(mapper.writeValueAsString(admin), expected);
	}// end of serializationTest method
	
	/**
	 * This is the test method from de-serializing Admin
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		Admin admin = mapper.readValue(json, Admin.class);
		assertEquals(1, admin.getAdminId());
	}// end of deSerializeTest method
}
