package com.example.courtathome.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "vehicleNo", scope = VehicleDetails.class)
public class VehicleDetails {

	@Id
	private String vehicleNo;
	private String vehicleType;

	@ManyToOne(cascade = CascadeType.ALL)
	private UserDetails userDetails;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleDetails")
	private List<CaseDetails> caseList;

	@Override
	public String toString() {
		return "VehicleInfo [vehicleId=" + ", vehicleNo=" + vehicleNo + ", vehicleType=" + vehicleType + ", userInfo="
				+ userDetails + "]";
	}

	public VehicleDetails(String vehicleNo, String vehicleType, List<CaseDetails> caseList) {
		super();
		this.vehicleNo = vehicleNo;
		this.vehicleType = vehicleType;
		this.caseList = caseList;
	}

	public VehicleDetails(String vehicleNo, String vehicleType, UserDetails userDetails, List<CaseDetails> caseList) {
		super();
		this.vehicleNo = vehicleNo;
		this.vehicleType = vehicleType;
		this.userDetails = userDetails;
		this.caseList = caseList;
	}

	public VehicleDetails(String vehicleNo, String vehicleType, UserDetails userDetails) {
		super();
		this.vehicleNo = vehicleNo;
		this.vehicleType = vehicleType;
		this.userDetails = userDetails;
	}

}
