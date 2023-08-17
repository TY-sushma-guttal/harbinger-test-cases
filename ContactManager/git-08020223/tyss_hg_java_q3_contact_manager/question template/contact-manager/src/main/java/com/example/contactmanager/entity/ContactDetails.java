package com.example.contactmanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "tbl_contact")
public class ContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "phoneNo", nullable = false)
	private Long phoneNo;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "address", nullable = false)
	private String address;

	@Lob
	@Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
	private byte[] image;

	public ContactDetails() {
	}

}
