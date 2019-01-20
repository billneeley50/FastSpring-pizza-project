package com.fastspring.pizza.Domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final IngredientsRepository ingredientsRepository;
	private final PizzaSizeRepository pizzaSizeRepository;
	private final PromotionsRepository promotionsRepository;


	@Autowired
	public DatabaseLoader(IngredientsRepository repository,
						  PizzaSizeRepository pizzaSizeRepository,
						  PromotionsRepository promotionsRepository) {
		this.ingredientsRepository = repository;
		this.pizzaSizeRepository = pizzaSizeRepository;
		this.promotionsRepository = promotionsRepository;

	}


	@Override
	public void run(String... strings) throws Exception {
		this.ingredientsRepository.save(new Ingredient("Cheese", 10, 1.00));
		this.ingredientsRepository.save(new Ingredient("Pepperoni", 8, .90));
		this.ingredientsRepository.save(new Ingredient("Sausage", 6, 1.10));
		this.ingredientsRepository.save(new Ingredient("Olives", 6, .50));
		this.ingredientsRepository.save(new Ingredient("Mushrooms", 5, .75));


		this.pizzaSizeRepository.save(new PizzaSize(PizzaSize.PIZZASIZE.SMALL.name(), 10.00, 10, 8));
		this.pizzaSizeRepository.save(new PizzaSize(PizzaSize.PIZZASIZE.MEDIUM.name(), 15.00, 12, 12));
		this.pizzaSizeRepository.save(new PizzaSize(PizzaSize.PIZZASIZE.LARGE.name(), 20.00, 15, 16));

		this.promotionsRepository.save(new Promotion("12345", 10));
		this.promotionsRepository.save(new Promotion("6789", 20));

	}
}