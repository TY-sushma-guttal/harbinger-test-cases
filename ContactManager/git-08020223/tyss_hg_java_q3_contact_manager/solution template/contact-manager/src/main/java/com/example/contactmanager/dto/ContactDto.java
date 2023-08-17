package com.example.contactmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
	private Long id;
	private String name;
	private String email;
	private Long phoneNo;
	private String address;
	private String description;
	private byte[] image;

}
