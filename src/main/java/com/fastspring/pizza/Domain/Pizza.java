
package com.fastspring.pizza.Domain;

import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

	public Double getTotalPrice() {
		return totalPrice;
	}

	@ManyToOne(targetEntity=Promotion.class, fetch=FetchType.EAGER)
	public Promotion promotion;

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}

	private @Id @GeneratedValue Long id;
	private String customerName;
	private String customerAddress;
	private String customerNumber;

	@ManyToOne(targetEntity=PizzaSize.class, fetch=FetchType.EAGER)
	public PizzaSize pizzaSize;

	private Integer discountPercent = 0;
	private Double totalPrice = 0.00;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;


	public Pizza(String customerName, String customerAddress, String customerNumber,
				 PizzaSize pizzaSize, Promotion promotion, Integer discountPercent, Double totalPrice) {
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerNumber = customerNumber;
		this.pizzaSize = pizzaSize;
		this.promotion = promotion;
		this.discountPercent = discountPercent;
		this.totalPrice = totalPrice;
	}


	@ManyToMany(fetch = FetchType.EAGER)
	private List<Ingredient> ingredients = new LinkedList<>();

	private Pizza() {}

	public PizzaSize getPizzaSize() {
		return pizzaSize;
	}

	public void setPizzaSize(PizzaSize pizzaSize) {
		this.pizzaSize = pizzaSize;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	@Transient
	public String getPromoCode() {
		return (promotion == null ? "" : promotion.getPromotionCode());
	}

	public Integer getDiscountPercent() {
		return discountPercent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	@Transient
	public Long getPizzaId() {
		return id;
	}

	@Transient
	public String getOrderDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		return dateFormat.format(this.getCreateDate());

	}

	@Transient
	public List<Ingredient> getIngredientList() {
		return ingredients;
	}
}