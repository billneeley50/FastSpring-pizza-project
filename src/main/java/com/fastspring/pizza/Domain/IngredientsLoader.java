package com.fastspring.pizza.Domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class IngredientsLoader implements CommandLineRunner {

	private final IngredientsRepository ingredientsRepository;


	@Autowired
	public IngredientsLoader(IngredientsRepository repository) {
		this.ingredientsRepository = repository;
	}


	@Override
	public void run(String... strings) throws Exception {
		this.ingredientsRepository.save(new Ingredient("Cheese", 10, 1.00));
		this.ingredientsRepository.save(new Ingredient("Pepperoni", 8, .90));
		this.ingredientsRepository.save(new Ingredient("Sausage", 6, 1.10));
		this.ingredientsRepository.save(new Ingredient("Olives", 6, .50));
		this.ingredientsRepository.save(new Ingredient("Mushrooms", 5, .75));

	}
}