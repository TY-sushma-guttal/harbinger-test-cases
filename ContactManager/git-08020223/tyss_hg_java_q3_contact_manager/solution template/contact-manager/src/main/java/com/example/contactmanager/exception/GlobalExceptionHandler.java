package com.example.contactmanager.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.example.contactmanager.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataAlreadyExist.class)
	public ModelAndView dataAlreadyExist(DataAlreadyExist exception) {
		ResponseDto responseDto = new ResponseDto(true, exception.getMessage(), null);
		ModelAndView view = new ModelAndView("error-message");
		view.addObject("error", responseDto);
		return view;

	}
}
