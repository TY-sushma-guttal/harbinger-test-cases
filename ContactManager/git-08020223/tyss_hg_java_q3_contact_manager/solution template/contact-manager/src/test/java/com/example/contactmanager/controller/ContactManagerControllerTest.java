package com.example.contactmanager.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.example.contactmanager.dto.ContactDto;
import com.example.contactmanager.entity.ContactDetails;
import com.example.contactmanager.service.ContactManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class ContactManagerControllerTest {

	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@Mock
	private ContactManagerService service;

	@InjectMocks
	private ContactManagerController controller;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	Model model = mock(Model.class);

	ContactDto contactDto = new ContactDto(1l, "sumeet", "mailto:s@gmail.com", 9874563217l, null, null,
			"sumeet".getBytes());
	List<ContactDto> list = new ArrayList<>();
	ContactDetails contactDetails = new ContactDetails(1l, "sumeet", "mailto:s@gmail.com", 9874563217L, null, null,
			"sumeet".getBytes());

	@Test
	void home() throws Exception {
		mockMvc.perform(get("/").flashAttr("model", model)).andExpect(status().isOk()).andDo(print());

	}

	@Test
	void getAddContactForm() throws Exception {
		Model model = mock(Model.class);

		mockMvc.perform(get("/showAddContactForm").flashAttr("model", model)).andExpect(status().isOk()).andDo(print())
				.andExpect(forwardedUrl("add-contact-form"));

	}

	@Test
	void add() throws Exception {
		MockMultipartFile file = new MockMultipartFile("photo", "filename.jpeg", MediaType.APPLICATION_JSON_VALUE,
				"sumeet".getBytes());
		Mockito.doNothing().when(service).add(any(), any(), any(), anyString());
		mockMvc.perform(multipart("/add").file(file).flashAttr("model", model)).andExpect(status().isOk())
				.andDo(print()).andExpect(forwardedUrl("images"));
	}

	@Test
	void getUpdateContactForm() throws Exception {
		Model model = mock(Model.class);
		mockMvc.perform(get("/showUpdateForm").flashAttr("model", model).param("email", "mailto:s@gmail.com"))
				.andExpect(status().isOk()).andDo(print()).andExpect(forwardedUrl("update-contact-form"));

	}

	@Test
	void update() throws Exception {
		MockMultipartFile file = new MockMultipartFile("photo", "filename.jpeg", MediaType.APPLICATION_JSON_VALUE,
				"sumeet".getBytes());
		when(service.update(any(), any(), any(), anyString())).thenReturn(contactDto);
		mockMvc.perform(multipart("/update").file(file).flashAttr("model", model)).andExpect(status().isOk())
				.andDo(print()).andExpect(forwardedUrl("images"));
	}

	@Test
	void delete() throws Exception {
		when(service.delete(anyString())).thenReturn(contactDto);
		mockMvc.perform(get("/delete").flashAttr("model", model).param("email", "mailto:s@gmail.com"))
				.andExpect(status().isOk()).andDo(print()).andExpect(forwardedUrl("images"));
	}

	@Test
	void show() throws Exception {
		list.add(contactDto);
		when(service.getAll()).thenReturn(list);
		mockMvc.perform(get("/list").flashAttr("model", model)).andExpect(status().isOk()).andDo(print())
				.andExpect(forwardedUrl("images"));

	}

	@Test
	void showImage() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		Optional<ContactDetails> optional = Optional.of(contactDetails);
		when(service.getImageById(anyLong())).thenReturn(optional);
		mockMvc.perform(get("/contact/display/1").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.IMAGE_JPEG_VALUE).content(objectMapper.writeValueAsString(optional.get())))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	}

	@Test
	void showContactDetails() throws Exception {
		Optional<ContactDetails> optional = Optional.of(contactDetails);
		when(service.getImageById(anyLong())).thenReturn(optional);
		mockMvc.perform(get("/image/imageDetails").flashAttr("model", model).param("id", "1"))
				.andExpect(status().isOk()).andDo(print()).andExpect(forwardedUrl("imagedetails"));
	}
}
