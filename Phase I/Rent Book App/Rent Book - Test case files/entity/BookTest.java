package com.example.rentbook.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.rentbook.pojo.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class BookTest {

	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"slNo\":1,\"bookName\":\"bookName\",\"authorName\":\"authorName\",\"publishedBy\":\"publishedBy\",\"publishedDate\":null,\"bookDescription\":\"bookDescription\",\"bookPrice\":200}\r\n"
			+ "";

	/**
	 * This is the test case for serializing Book entity
	 * @throws Exception
	 */
	@Test
	void serializationTest() throws Exception {
		Book book = new Book(1, "bookName", "authorName", "publishedBy", null, "bookDescription", 200);
		String expected = mapper.writeValueAsString(mapper.readValue(json, Book.class));
		assertEquals(mapper.writeValueAsString(book), expected);
	}// end of serializationTest method

	
	/**
	 * This is the test case for de-serializing Book entity
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		Book book = mapper.readValue(json, Book.class);
		assertEquals("bookName", book.getBookName());
	}// end of deSerializeTest method

}
