package com.example.contactmanager.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.contactmanager.dto.ContactDto;
import com.example.contactmanager.entity.ContactDetails;
import com.example.contactmanager.exception.DataAlreadyExist;
import com.example.contactmanager.repository.ContactDetailsRepository;

@Service
public class ContactManagerService {

	@Autowired
	private ContactDetailsRepository contactDetailsRepository;

	public List<ContactDetails> getAllActiveImages() {
		return contactDetailsRepository.findAll();
	}

	public Optional<ContactDetails> getImageById(Long id) {
		return contactDetailsRepository.findById(id);
	}

	public void add(ContactDto contactDto, MultipartFile multipartFile, HttpServletRequest request,
			String uploadFolder) {

		if (contactDetailsRepository.findByEmail(contactDto.getEmail()).isPresent())
			throw new DataAlreadyExist("Email id already exists");
		try {
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			String fileName = multipartFile.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			if (fileName == null || fileName.contains("..")) {

				throw new DataAlreadyExist("Sorry! Filename contains invalid path sequence ");
			}

			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(multipartFile.getBytes());
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			byte[] imageData = multipartFile.getBytes();
			ContactDetails contactDetails = new ContactDetails();
			BeanUtils.copyProperties(contactDto, contactDetails);
			contactDetails.setImage(imageData);
			contactDetailsRepository.save(contactDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<ContactDto> getAll() {
		List<ContactDetails> findAll = contactDetailsRepository.findAll();
		List<ContactDto> contactDtoList = new ArrayList<>();
		findAll.forEach(contact -> {
			ContactDto contactDto = new ContactDto();
			BeanUtils.copyProperties(contact, contactDto);
			contactDtoList.add(contactDto);
		});
		return contactDtoList;
	}

	public ContactDto getByEmail(String email) {
		Optional<ContactDetails> findByEmail = contactDetailsRepository.findByEmail(email);
		ContactDetails contact = findByEmail.get();
		ContactDto contactDto = new ContactDto();
		BeanUtils.copyProperties(contact, contactDto);
		return contactDto;
	}

	public ContactDto update(ContactDto contactDto, MultipartFile multipartFile, HttpServletRequest request,
			String uploadFolder) throws IOException {

		String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
		String fileName = multipartFile.getOriginalFilename();
		String filePath = Paths.get(uploadDirectory, fileName).toString();

		try {
			File dir = new File(uploadDirectory);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream.write(multipartFile.getBytes());
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Optional<ContactDetails> findByEmail = contactDetailsRepository.findById(contactDto.getId());
		ContactDetails contact = findByEmail.get();
		if (multipartFile.isEmpty()) {
			contactDto.setImage(contact.getImage());
		} else {
			contactDto.setImage(multipartFile.getBytes());
		}
		BeanUtils.copyProperties(contactDto, contact);
		ContactDetails save = contactDetailsRepository.save(contact);
		BeanUtils.copyProperties(save, contactDto);
		return contactDto;

	}

	public ContactDto delete(String email) {
		Optional<ContactDetails> findByemail = contactDetailsRepository.findByEmail(email);
		contactDetailsRepository.delete(findByemail.get());
		return new ContactDto();

	}
}
