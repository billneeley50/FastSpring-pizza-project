package com.fastspring.pizza.Domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PromotionsLoader implements CommandLineRunner {

	private final PromotionsRepository promotionsRepository;


	@Autowired
	public PromotionsLoader(PromotionsRepository repository) {
		this.promotionsRepository = repository;
	}


	@Override
	public void run(String... strings) throws Exception {
		this.promotionsRepository.save(new Promotion("12345", 10));
		this.promotionsRepository.save(new Promotion("6789", 20));

	}
}