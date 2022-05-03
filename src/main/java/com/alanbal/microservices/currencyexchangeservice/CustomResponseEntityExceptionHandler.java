package com.alanbal.microservices.currencyexchangeservice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alanbal.microservices.currencyexchangeservice.exception.RemoteServiceException;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(),
				request.getDescription(false), "Application continues as usual");

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<>(exceptionResponse,
				HttpStatus.INTERNAL_SERVER_ERROR);

		return responseEntity;
	}

	@ExceptionHandler(CurrencyNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(Exception e, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(),
				request.getDescription(false), "The requested Exchange does not exist.");

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<>(exceptionResponse,
				HttpStatus.NOT_FOUND);

		return responseEntity;
	}

	@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
	@ExceptionHandler(RemoteServiceException.class)
	public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(RemoteServiceException e,
			WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(),
				request.getDescription(false), "Failed Dependecy.");

		ResponseEntity<ExceptionResponse> responseEntity = new ResponseEntity<>(exceptionResponse,
				HttpStatus.FAILED_DEPENDENCY);

		return responseEntity;
	}

}
