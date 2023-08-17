package com.tyss.courtathome.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.courtathome.dto.UserDetailsDto;
import com.example.courtathome.entity.CaseDetails;
import com.example.courtathome.entity.UserDetails;
import com.example.courtathome.entity.VehicleDetails;
import com.example.courtathome.exception.LoginFailedException;
import com.example.courtathome.reposiotry.UserDetailsRepository;
import com.example.courtathome.reposiotry.VehicleDetailsRepository;
import com.example.courtathome.service.UserDetailsServiceImpl;

@SpringBootTest
class UserDetailsServiceImplTest {

	@InjectMocks
	private UserDetailsServiceImpl serviceImpl;

	@Mock
	private UserDetailsRepository userDetailsRepository;

	@Mock
	private VehicleDetailsRepository vehicleDetailsRepository;

	VehicleDetails vehicleDetails = new VehicleDetails("KA-01-SS-7894", "Two-Wheeler", null, null);
	List<VehicleDetails> list = new ArrayList<>();
	UserDetails userDetails = new UserDetails(1, "Sumeet", "987456321", "s@gmail.com", "Bengaluru", "123", list, null);
	CaseDetails caseDetails = new CaseDetails(1, "OverSpeed", null, "Active", 1000.00, vehicleDetails, userDetails);
	UserDetailsDto userDetailsDto = new UserDetailsDto("Sumeet", "987456321", "s@gmail.com", "123", "Bengaluru",
			"Two-Wheeler", "KA-01-SS-7894");

	@Test
	void register_success() {
		list.add(vehicleDetails);
		Optional<UserDetails> optionalEmpty = Optional.empty();
		when(userDetailsRepository.findByMobileNo(anyString())).thenReturn(optionalEmpty);
		userDetails.setVehicleDetails(list);
		when(userDetailsRepository.save(any())).thenReturn(userDetails);
		UserDetailsDto register = serviceImpl.register(userDetailsDto);
		assertEquals("Sumeet", register.getName());
	}

	@Test
	void register_success1() {
		list.add(vehicleDetails);
		Optional<UserDetails> optional = Optional.of(userDetails);
		when(userDetailsRepository.findByMobileNo(anyString())).thenReturn(optional);
		when(userDetailsRepository.save(any())).thenReturn(userDetails);
		UserDetailsDto register = serviceImpl.register(userDetailsDto);
		assertEquals("Sumeet", register.getName());
	}

	@Test
	void login_success() {
		Optional<UserDetails> optional = Optional.of(userDetails);
		when(userDetailsRepository.findByVehicleDetailsVehicleNoOrMobileNoAndPassword(anyString(), anyString(),
				anyString())).thenReturn(optional);
		UserDetailsDto login = serviceImpl.login("KA-01-SS-7894", "987456321", "123");
		assertEquals("Sumeet", login.getName());
	}

	@Test
	void login_fail() {
		Optional<UserDetails> optional = Optional.empty();
		when(userDetailsRepository.findByMobileNo(anyString())).thenReturn(optional);
		assertThatThrownBy(() -> serviceImpl.login(null, null, null)).isInstanceOf(LoginFailedException.class);
	}

}
