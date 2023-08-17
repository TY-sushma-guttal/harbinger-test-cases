package com.example.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.demo.pojo.Product;
import com.example.demo.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ProductTest {

	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"productId\":1,\"productName\":\"productname\"}";

	
	/**
	 * This is the test case for serializing the Product entity
	 * @throws Exception
	 */
	@Test
	void serializationTest() throws Exception {
		Product product = new Product(1l, "productname", new User());
		String expected = mapper.writeValueAsString(mapper.readValue(json, Product.class));
		assertEquals(mapper.writeValueAsString(product), expected);
	}

	/**
	 * This is the test case for de-serializing the Product entity
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		Product product = mapper.readValue(json, Product.class);
		assertEquals("productname", product.getProductName());
	}

}
