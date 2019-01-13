
package com.fastspring.pizza.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "pizzasizes")
public class PizzaSize {

	public Long getId() {
		return id;
	}

	private @Id @GeneratedValue Long id;
	private PIZZASIZE pizzaSize;
	private Double price = 0.0;
	private int width = 0;
	private int slices = 0;

	private PizzaSize() {}

	public PizzaSize(PIZZASIZE pizzaSize, Double price, int width, int slices) {
		this.pizzaSize = pizzaSize;
		this.price = price;
		this.width = width;
		this.slices = slices;
	}

	public enum PIZZASIZE {
		SMALL, MEDIUM, LARGE
	}
}