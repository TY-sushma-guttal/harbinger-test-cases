package com.example.courtathome.reposiotry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.courtathome.entity.VehicleDetails;

public interface VehicleDetailsRepository extends JpaRepository<VehicleDetails, String> {

	Optional<VehicleDetails> findByVehicleNo(String no);

}
