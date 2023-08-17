package com.example.courtathome.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId", scope = UserDetails.class)
public class UserDetails {

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", name=" + name + ", mobileNo=" + mobileNo + ", email=" + email
				+ ", address=" + address + ", password=" + password + "]";
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer userId;
	private String name;
	private String mobileNo;
	private String email;
	private String address;
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetails")
	private List<VehicleDetails> vehicleDetails;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetails")
	private List<CaseDetails> caseDetails;

}
