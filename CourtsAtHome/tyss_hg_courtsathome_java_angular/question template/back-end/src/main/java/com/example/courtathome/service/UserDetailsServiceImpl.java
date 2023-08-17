package com.example.courtathome.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.courtathome.constant.Constant;
import com.example.courtathome.constant.ExceptionConstant;
import com.example.courtathome.dto.UserDetailsDto;
import com.example.courtathome.entity.UserDetails;
import com.example.courtathome.entity.VehicleDetails;
import com.example.courtathome.exception.LoginFailedException;
import com.example.courtathome.reposiotry.UserDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetailsDto register(UserDetailsDto userDetailsDto) {
		log.info(Constant.USER_REGISTER_SERVICE);
		Optional<UserDetails> findByEmail = userDetailsRepository.findByMobileNo(userDetailsDto.getMobileNo());
		if (findByEmail.isEmpty()) {
			UserDetails userDetails = new UserDetails();
			BeanUtils.copyProperties(userDetailsDto, userDetails);
			VehicleDetails vehicleDetail = new VehicleDetails(userDetailsDto.getVehicleNo(),
					userDetailsDto.getVehicleType(), userDetails);
			List<VehicleDetails> vehicleDetails = List.of(vehicleDetail);
			userDetails.setVehicleDetails(vehicleDetails);
			UserDetails save = userDetailsRepository.save(userDetails);
			BeanUtils.copyProperties(save, userDetailsDto);
			return userDetailsDto;
		}
		UserDetails userDetails = findByEmail.get();
		BeanUtils.copyProperties(userDetailsDto, userDetails);
		VehicleDetails vehicleDetail = new VehicleDetails(userDetailsDto.getVehicleNo(),
				userDetailsDto.getVehicleType(), userDetails);
		userDetails.getVehicleDetails().add(vehicleDetail);
		UserDetails save = userDetailsRepository.save(userDetails);
		BeanUtils.copyProperties(save, userDetailsDto);
		return userDetailsDto;
	}

	@Override
	public UserDetailsDto login(String vehicleNo, String mobileNo, String password) {
		log.info(Constant.USER_LOGIN_SERVICE);
		Optional<UserDetails> findByPhoneNoAndPassword = userDetailsRepository
				.findByVehicleDetailsVehicleNoOrMobileNoAndPassword(vehicleNo, mobileNo, password);
		if (findByPhoneNoAndPassword.isPresent() && findByPhoneNoAndPassword.get().getPassword().equals(password)) {
			UserDetailsDto userDetailsDto = new UserDetailsDto();
			log.info(Constant.FETCHED, findByPhoneNoAndPassword.get());
			BeanUtils.copyProperties(findByPhoneNoAndPassword.get(), userDetailsDto);
			return userDetailsDto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new LoginFailedException(ExceptionConstant.LOGIN_FAILED);
	}

}
