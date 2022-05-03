package com.alanbal.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alanbal.microservices.currencyexchangeservice.exception.RemoteServiceException;

import io.github.resilience4j.retry.annotation.Retry;


@RestController
public class CircuitBreakerController {

	Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
	@Retry(name = "sample-api", fallbackMethod = "defaultResponse")
	public String sampleApi() {

		logger.info("Into the sample API");
		ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8081/dummy/stuff",
			String.class);

		return entity.getBody();
	}
	
	/**
	 * This method is used as a default response after the retries attemps to hit an specific service fails
	 * **/
	@SuppressWarnings(value = {"unused" })
	private String defaultResponse(Exception e) {
		return "We could not get a response from a remote service.";
	}

}
