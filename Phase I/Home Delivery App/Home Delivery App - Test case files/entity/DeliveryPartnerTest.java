package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.demo.pojo.DeliveryPartner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class DeliveryPartnerTest {
	
	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"deliveryPartnerId\":1,\"deliveryPartnerName\":\"name\",\"location\":\"location\",\"availableFrom\":null,\"availableTo\":null,\"rating\":4.3,\"users\":[]}";
	
	
	/**
	 * This is the test case for serializing the DeliveryPartner entity
	 * @throws Exception
	 */
	@Test
	void serializationTest() throws Exception {
		DeliveryPartner deliveryPartner = new DeliveryPartner(1l, "name", "location", null, null, 4.3, new ArrayList<>());
		String expected = mapper.writeValueAsString(mapper.readValue(json, DeliveryPartner.class));
		assertEquals(mapper.writeValueAsString(deliveryPartner), expected);
	}// end of serializationTest method

	
	/**
	 * This is the test case for de-serializing the DeliveryPartner entity
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		DeliveryPartner deliveryPartner = mapper.readValue(json, DeliveryPartner.class);
		assertEquals(4.3, deliveryPartner.getRating());
	}// end of deSerializeTest method


}
