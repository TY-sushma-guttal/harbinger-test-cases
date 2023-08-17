package com.tyss.courtathome.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.courtathome.controller.UserDetailsController;
import com.example.courtathome.dto.ResponseDto;
import com.example.courtathome.dto.UserDetailsDto;
import com.example.courtathome.service.UserDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class UserDetailsControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserDetailsService service;

	@InjectMocks
	private UserDetailsController controller;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	UserDetailsDto userDetailsDto = new UserDetailsDto("Sumeet", "987456321", "s@gmail.com", "123", "Bengaluru",
			"Two-Wheeler", "KA-01-SS-2134");

	@Test
	void userRegister_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(service.register(any())).thenReturn(userDetailsDto);
		String contentAsString = mockMvc
				.perform(post("/api/v1/register").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userDetailsDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Registration Successfull", readValue.getMsg());
	}

	@Test
	void login_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(service.login(anyString(), anyString(), anyString())).thenReturn(userDetailsDto);
		String contentAsString = mockMvc
				.perform(get("/api/v1/login").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userDetailsDto)).param("phoneNo", "987456321")
						.param("password", "123").param("vehicleNo", "KA-01-SS-2134"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("User Login Successfull", readValue.getMsg());
	}
}
