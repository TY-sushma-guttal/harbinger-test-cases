package com.example.courtathome.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class VehicleNotFoundException extends RuntimeException {

	private final String message;
}
