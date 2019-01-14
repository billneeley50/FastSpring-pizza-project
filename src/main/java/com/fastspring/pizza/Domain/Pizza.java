
package com.fastspring.pizza.Domain;

import javax.persistence.*;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "pizzas")
public class Pizza {

	public Long getId() {
		return id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public PizzaSize.PIZZASIZE getPizzaSize() {
		return pizzaSize;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	private @Id @GeneratedValue Long id;
	private String customerName;
	private String customerAddress;
	private String customerNumber;

	public Pizza(String customerName, String customerAddress, String customerNumber, PizzaSize.PIZZASIZE pizzaSize, Double totalPrice) {
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerNumber = customerNumber;
		this.pizzaSize = pizzaSize;
		this.totalPrice = totalPrice;
	}

	private PizzaSize.PIZZASIZE pizzaSize;
	private Double totalPrice = 0.00;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "pizza_ingredients",
			joinColumns = { @JoinColumn(name = "pizza_id") },
			inverseJoinColumns = { @JoinColumn(name = "ingredient_id") })
	private Set<Ingredient> ingredients = new HashSet<>();

	private Pizza() {}

}