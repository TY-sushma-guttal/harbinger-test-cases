package com.example.rentbook.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.rentbook.pojo.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class NotificationTest {

	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"notificationId\":1,\"description\":\"description\",\"type\":\"type\",\"id\":1}";
	
	/**
	 * This is the test case for serializing Notification entity
	 * @throws Exception
	 */
	@Test
	void serializationTest() throws Exception {
		Notification notification = new Notification(1, "description", "type", 1);
		String expected = mapper.writeValueAsString(mapper.readValue(json, Notification.class));
		assertEquals(mapper.writeValueAsString(notification), expected);
	}// end of serializationTest method
	
	
	/**
	 * This is the test case for de-serializing Notification entity
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		Notification notification = mapper.readValue(json, Notification.class);
		assertEquals(1, notification.getId());
	}// end of deSerializeTest method
}
