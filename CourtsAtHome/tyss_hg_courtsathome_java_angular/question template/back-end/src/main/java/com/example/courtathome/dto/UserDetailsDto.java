package com.example.courtathome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {

	private String name;
	private String mobileNo;
	private String email;
	private String password;
	private String address;
	private String vehicleType;
	private String vehicleNo;
}
