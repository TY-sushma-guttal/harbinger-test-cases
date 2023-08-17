package com.example.courtathome.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class SomethingWentWrongException extends RuntimeException {

	private final String message;
}
