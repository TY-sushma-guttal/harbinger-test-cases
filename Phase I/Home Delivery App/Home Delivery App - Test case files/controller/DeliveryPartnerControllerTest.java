package com.example.demo.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.pojo.User;
import com.example.demo.service.DeliveryPartnerService;

@ExtendWith(MockitoExtension.class)
class DeliveryPartnerControllerTest {
	

	@InjectMocks
	private DeliveryPartnerController deliveryPartnerController;

	@Mock
	private DeliveryPartnerService deliveryPartnerService;

	private MockMvc mockMvc;
	
	private User user;
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(deliveryPartnerController).build();
		user = new User(1l, "userName", 9876543212l, "user@gmail.com", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	}

	/**
	 * This is the test case for assignDelivery API
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@Test
	void testAssignDelivery() throws UnsupportedEncodingException, Exception {
		
		Model model=mock(Model.class);
		BindingResult result = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		HashMap<String, Object> flashAttr = new HashMap<>();
		flashAttr.put("model", model);
		flashAttr.put("result", result);
		flashAttr.put("redirectAttributes", redirectAttributes);
		
		mockMvc.perform(post("/delivery-partner/assign/delivery")
						.flashAttrs(flashAttr)
						.param("userId", "1")
		                .param("deliveryPartnerId", "1")
		                .param("userAddress", "type")
		                .param("requiredFromDate", "2022-01-01")
		                .param("requiredToDate", "2022-01-01")
		                .param("requiredFromTime", "01:30:30")
		                .param("requiredToTime", "01:30:30")
		                .sessionAttr("user", user))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("assign-delivery"))
				.andExpect(model().size(12))
				.andDo(print())
				.andExpect(model().attributeExists("userId"))
				.andExpect(model().attributeExists("redirectAttributes"))
				;
	}

	
	
	
	/**
	 * This is the test case for getAllDeliveryPartners API
	 * @throws Exception
	 */
	@Test
	void testGetAllDeliveryPartners() throws Exception {
		Model model=mock(Model.class);
		
		mockMvc.perform(post("/delivery-partner/get")
				.flashAttr("model", model)
				.param("userId", "1"))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(forwardedUrl("list-delivery-partners"));
	}

}
