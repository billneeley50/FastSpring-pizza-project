
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
	private PIZZASIZE size;
	private Double price = 0.0;
	private int width = 0;
	private int slices = 0;

	private PizzaSize() {}

	public PizzaSize(String size, Double price, int width, int slices) {
		this.size = PIZZASIZE.valueOf(size);
		this.price = price;
		this.width = width;
		this.slices = slices;
	}

	public PIZZASIZE getSize() {
		return size;
	}

	public Double getPrice() {
		return price;
	}

	public int getWidth() {
		return width;
	}

	public int getSlices() {
		return slices;
	}

	public enum PIZZASIZE {
		SMALL, MEDIUM, LARGE
	}
}