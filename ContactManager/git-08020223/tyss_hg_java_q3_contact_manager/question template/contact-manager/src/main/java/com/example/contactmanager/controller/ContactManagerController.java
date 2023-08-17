package com.example.contactmanager.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.contactmanager.constant.Constant;
import com.example.contactmanager.dto.ContactDto;
import com.example.contactmanager.entity.ContactDetails;
import com.example.contactmanager.service.ContactManagerService;

@Controller
public class ContactManagerController {

	@Value("${uploadDir}")
	private String uploadFolder;

	@Autowired
	private ContactManagerService contactService;

	@GetMapping(value = { "/", "/home" })
	public String addProductPage() {
		return "index";
	}

	@GetMapping("showAddContactForm")
	public ModelAndView getAddContactForm() {
		ModelAndView view = new ModelAndView("add-contact-form");
		ContactDto contactDto = new ContactDto();
		view.addObject("contact", contactDto);
		return view;
	}

	@PostMapping("/add")
	public ModelAndView addContact(@ModelAttribute ContactDto contactDto,
			@RequestPart("photo") MultipartFile multipartFile, HttpServletRequest request) {
		contactService.add(contactDto, multipartFile, request, uploadFolder);
		return new ModelAndView(Constant.IMAGES).addObject(Constant.CONTACTS_HTML_ATTRIBUTE, contactService.getAll());
	}

	@GetMapping("/contact/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<ContactDetails> imageGallery)
			throws IOException {
		imageGallery = contactService.getImageById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(imageGallery.get().getImage());
		response.getOutputStream().close();
	}

	@GetMapping("/image/imageDetails")
	String showContactDetails(@RequestParam("id") Long id, Optional<ContactDetails> imageGallery, Model model) {
		try {
			if (id != 0) {
				imageGallery = contactService.getImageById(id);

				if (imageGallery.isPresent()) {
					model.addAttribute("id", imageGallery.get().getId());
					model.addAttribute("description", imageGallery.get().getDescription());
					model.addAttribute("name", imageGallery.get().getName());
					return "imagedetails";
				}
				return Constant.REDIRECT_HOME;
			}
			return Constant.REDIRECT_HOME;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.REDIRECT_HOME;
		}
	}

	/*
	 * COMPLETE THE LOGIC FOR GET_UPDATE_FORM API BELOW
	 */
	@GetMapping("/showUpdateForm")
	public ModelAndView getUpdateContactForm(@RequestParam(value = "email", required = false) String email) {
		return null;
	}

	/*
	 * COMPLETE THE LOGIC FOR UPDATE API BELOW
	 */
	@PostMapping("/update")
	public ModelAndView update(@ModelAttribute ContactDto contactDto) {
		return null;
	}

	/*
	 * COMPLETE THE LOGIC FOR DELETE API BELOW
	 */
	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "email", required = false) String email) {
		return null;
	}

	/*
	 * COMPLETE THE LOGIC FOR SHOW API BELOW
	 */
	@GetMapping("/list")
	String show(Model map) {
		return null;
	}
}
