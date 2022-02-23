package com.alanbal.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alanbal.microservices.currencyexchangeservice.CurrencyNotFoundException;
import com.alanbal.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.alanbal.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
@RequestMapping("currency-exchange")
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeRepository repository;
	
	///from/USD/to/INR
	@GetMapping("/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable("from") String from,@PathVariable("to") String to) throws CurrencyNotFoundException{
		

		//CurrencyExchange currencyExchange = new CurrencyExchange(1L,from, to, BigDecimal.valueOf(50));
		
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		
		if(currencyExchange == null)
			throw new CurrencyNotFoundException("Currency exchange could not be found");
		
		String port = environment.getProperty("local.server.port");
		
		currencyExchange.setEnvironment(port);
		
		return currencyExchange;
	}
}
