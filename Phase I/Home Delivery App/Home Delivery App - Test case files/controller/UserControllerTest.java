package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	
	@InjectMocks
	private UserController UserController;

	@Mock
	private UserService userService;

	private MockMvc mockMvc;
	
	private User user;
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(UserController).build();
		user = new User(1l, "userName", 9876543212l, "user@gmail.com", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	}

	
	/**
	 * This is the test case for getHomePage API
	 * @throws Exception
	 */
	@Test
	void testGetHomePage() throws Exception {
		mockMvc.perform(post("/user/get-all"))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(forwardedUrl("list-users"))
				.andExpect(model().attributeExists("users"));
	}

	
	/**
	 * This is the test case for getUserDetails API
	 * @throws Exception
	 */
	@Test
	void testGetUserDetails() throws Exception {
		mockMvc.perform(get("/user/"))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(forwardedUrl("index"));
	}

}
