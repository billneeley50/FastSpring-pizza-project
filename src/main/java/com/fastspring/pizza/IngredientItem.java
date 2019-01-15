package com.fastspring.pizza;


import com.fastspring.pizza.Domain.Ingredient;

public class IngredientItem extends DomainItem {

    private Long id;
    private String name;
    private Integer inventory;
    private Double price;

    public IngredientItem() {}

    public IngredientItem(String message) {
        super(message);
    }

    public IngredientItem(Ingredient ingredient) {

        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.inventory = ingredient.getInventory();
        this.price = ingredient.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
