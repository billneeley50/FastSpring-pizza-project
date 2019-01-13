package com.fastspring.pizza.Domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PizzaLoader implements CommandLineRunner {

	private final PizzaRepository pizzaRepository;


	@Autowired
	public PizzaLoader(PizzaRepository repository) {
		this.pizzaRepository = repository;
	}


	@Override
	public void run(String... strings) throws Exception {
		this.pizzaRepository.save(new Pizza("Joe", "Jones", "Customer", PizzaSize.PIZZASIZE.SMALL));
		this.pizzaRepository.save(new Pizza("Bob", "Jones", "Customer", PizzaSize.PIZZASIZE.MEDIUM));
	}
}