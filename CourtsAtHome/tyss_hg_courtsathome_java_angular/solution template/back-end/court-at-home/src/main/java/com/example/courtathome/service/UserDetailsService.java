package com.example.courtathome.service;

import com.example.courtathome.dto.UserDetailsDto;

public interface UserDetailsService {

	UserDetailsDto register(UserDetailsDto userDetailsDto);

	UserDetailsDto login(String vehicleNo, String phoneNo, String password);

}
