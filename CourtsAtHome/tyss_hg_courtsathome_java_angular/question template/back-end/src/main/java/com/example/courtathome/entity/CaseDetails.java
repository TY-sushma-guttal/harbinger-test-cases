package com.example.courtathome.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "caseId", scope = CaseDetails.class)
public class CaseDetails {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer caseId;
	private String caseName;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "datetime")
	private LocalDateTime dateTime;
	private String status;
	private Double fine;

	@ManyToOne(cascade = CascadeType.ALL)
	private VehicleDetails vehicleDetails;

	@ManyToOne(cascade = CascadeType.ALL)
	private UserDetails userDetails;

}
