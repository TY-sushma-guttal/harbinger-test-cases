package com.example.courtathome.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseDetailsDto {

	private Integer caseId;
	private String caseName;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dateTime;
	private String status;
	private Double fine;
	private String vehicleNo;
	private String mobileNo;
}
