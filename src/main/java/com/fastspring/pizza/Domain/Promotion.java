
package com.fastspring.pizza.Domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "promotions")
public class Promotion {

	public Long getId() {
		return id;
	}

	private @Id @GeneratedValue Long id;
	private String promotionCode;
	private Integer discountPercent = 0;

	public String getPromotionCode() {
		return promotionCode;
	}

	public Integer getDiscountPercent() {
		return discountPercent;
	}


	public Promotion(String promotionCode, int discountPercent) {
		this.promotionCode = promotionCode;
		this.discountPercent = discountPercent;
	}
}