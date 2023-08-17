package com.example.courtathome.reposiotry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.courtathome.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {

	Optional<UserDetails> findByMobileNo(String mobileNo);

	Optional<UserDetails> findByVehicleDetailsVehicleNoOrMobileNoAndPassword(String vehicleNo, String phoneNo,
			String password);

}
