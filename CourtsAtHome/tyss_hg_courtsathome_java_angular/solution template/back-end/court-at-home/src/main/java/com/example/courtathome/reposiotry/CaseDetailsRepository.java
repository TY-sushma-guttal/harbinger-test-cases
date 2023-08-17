package com.example.courtathome.reposiotry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.courtathome.entity.CaseDetails;

public interface CaseDetailsRepository extends JpaRepository<CaseDetails, Integer> {

	List<CaseDetails> findByVehicleDetailsVehicleNoOrUserDetailsMobileNo(String vehicleNo, String mobileNo);

}
