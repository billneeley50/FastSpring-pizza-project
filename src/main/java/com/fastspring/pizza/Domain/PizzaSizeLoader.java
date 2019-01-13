package com.fastspring.pizza.Domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PizzaSizeLoader implements CommandLineRunner {

	private final PizzaSizeRepository pizzaSizeRepository;


	@Autowired
	public PizzaSizeLoader(PizzaSizeRepository repository) {
		this.pizzaSizeRepository = repository;
	}


	@Override
	public void run(String... strings) throws Exception {
		this.pizzaSizeRepository.save(new PizzaSize(PizzaSize.PIZZASIZE.SMALL, 10.00, 10, 8));
		this.pizzaSizeRepository.save(new PizzaSize(PizzaSize.PIZZASIZE.MEDIUM, 15.00, 12, 12));
		this.pizzaSizeRepository.save(new PizzaSize(PizzaSize.PIZZASIZE.LARGE, 20.00, 15, 16));

	}
}