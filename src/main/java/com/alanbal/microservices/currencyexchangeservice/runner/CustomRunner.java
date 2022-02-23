package com.alanbal.microservices.currencyexchangeservice.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alanbal.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@Component
public class CustomRunner implements CommandLineRunner{

	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
//		
//		System.out.println("Here in the runner");
//		repository.findAll().stream().forEach(System.out::println);
	}
}
