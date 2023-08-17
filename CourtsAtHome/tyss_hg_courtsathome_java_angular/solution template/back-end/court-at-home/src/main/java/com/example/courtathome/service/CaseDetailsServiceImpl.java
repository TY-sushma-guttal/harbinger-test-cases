package com.example.courtathome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.courtathome.constant.Constant;
import com.example.courtathome.constant.ExceptionConstant;
import com.example.courtathome.dto.CaseDetailsDto;
import com.example.courtathome.entity.CaseDetails;
import com.example.courtathome.entity.UserDetails;
import com.example.courtathome.entity.VehicleDetails;
import com.example.courtathome.exception.CaseNotfoundException;
import com.example.courtathome.exception.SomethingWentWrongException;
import com.example.courtathome.exception.UserNotPresentException;
import com.example.courtathome.exception.VehicleNotFoundException;
import com.example.courtathome.reposiotry.CaseDetailsRepository;
import com.example.courtathome.reposiotry.UserDetailsRepository;
import com.example.courtathome.reposiotry.VehicleDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CaseDetailsServiceImpl implements CaseDetailsService {

	@Autowired
	private CaseDetailsRepository caseDetailsRepository;

	@Autowired
	private VehicleDetailsRepository vehicleDetailsRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	public CaseDetailsDto registerCaseDetails(CaseDetailsDto caseDetailsDto) {
		log.info(Constant.CASEINFO_REGISTER_SERVICE);

		Optional<VehicleDetails> findByVehicleNo = Optional
				.ofNullable(vehicleDetailsRepository.findByVehicleNo(caseDetailsDto.getVehicleNo())
						.orElseThrow(() -> new VehicleNotFoundException(ExceptionConstant.VEHICLE_NOT_FOUND)));

		Optional<UserDetails> findByEmail = Optional
				.ofNullable(userDetailsRepository.findByMobileNo(caseDetailsDto.getMobileNo())
						.orElseThrow(() -> new UserNotPresentException(ExceptionConstant.USER_NOT_FOUND)));

		VehicleDetails vehicleDetails = findByVehicleNo.get();
		UserDetails userDetails = findByEmail.get();

		List<CaseDetails> caseInfoList = new ArrayList<>();
		CaseDetails newCaseInfo = new CaseDetails();
		newCaseInfo.setVehicleDetails(vehicleDetails);
		newCaseInfo.setUserDetails(userDetails);
		BeanUtils.copyProperties(caseDetailsDto, newCaseInfo);
		caseInfoList.add(newCaseInfo);
		vehicleDetails.setCaseList(caseInfoList);
		userDetails.setCaseDetails(caseInfoList);
		List<CaseDetails> caseList = vehicleDetails.getCaseList();
		caseDetailsRepository.saveAll(caseList);
		BeanUtils.copyProperties(newCaseInfo, caseDetailsDto);
		return caseDetailsDto;

	}

	@Override
	public List<CaseDetailsDto> getCaseDetails(String mobileNo, String vehicleNo) {
		log.info(Constant.CASEINFO_GETCASE_STATUS_SERVICE);

		List<CaseDetails> findByMobileNoOrVehicleNo = caseDetailsRepository
				.findByVehicleDetailsVehicleNoOrUserDetailsMobileNo(vehicleNo, mobileNo);

		List<CaseDetailsDto> caseDetailsDtos = new ArrayList<>();
		if (!findByMobileNoOrVehicleNo.isEmpty()) {
			log.info("findByStatusAndVehicleNo : {} ", findByMobileNoOrVehicleNo);
			findByMobileNoOrVehicleNo.forEach(casedetail -> {
				CaseDetailsDto caseDetailsDto = new CaseDetailsDto();
				BeanUtils.copyProperties(casedetail, caseDetailsDto);
				caseDetailsDto.setVehicleNo(casedetail.getVehicleDetails().getVehicleNo());
				caseDetailsDto.setMobileNo(casedetail.getUserDetails().getMobileNo());
				caseDetailsDtos.add(caseDetailsDto);
			});
			return caseDetailsDtos;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new CaseNotfoundException(ExceptionConstant.NO_ACTIVE_CASES);
	}

	@Override
	public CaseDetailsDto updateCaseDetails(CaseDetailsDto caseDetailsDto) {
		log.info(Constant.CASEINFO_UPDATE_STATUS_SERVICE);
		Optional<CaseDetails> findById = caseDetailsRepository.findById(caseDetailsDto.getCaseId());
		if (findById.isPresent()) {
			log.info("findById : {} ", findById.get());
			findById.get().setStatus("Closed");
			CaseDetails save = caseDetailsRepository.save(findById.get());
			BeanUtils.copyProperties(save, caseDetailsDto);
			caseDetailsDto.setVehicleNo(findById.get().getVehicleDetails().getVehicleNo());
			caseDetailsDto.setMobileNo(findById.get().getUserDetails().getMobileNo());
			return caseDetailsDto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new SomethingWentWrongException(ExceptionConstant.SOMETHING_WENT_WRONG);
	}

}