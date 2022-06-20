package com.infy.ms.order.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public void throwException() throws Exception {
		throw new Exception("Some Internal Error Occurred");
	}
}
