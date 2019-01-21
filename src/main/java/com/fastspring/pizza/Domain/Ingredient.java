package com.fastspring.pizza.Domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "ingredients")

public class Ingredient {

    @Column(name="id")
    @Id
    @GeneratedValue
    @Basic(optional = false)
    private Long id;

    @Column(name="name", unique = true)
    private String name;

    @Column(name="inventory")
    private Integer inventory;

    @Column(name="price")
    private Double price;

    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    private List<Pizza> pizzas = new LinkedList<>();


    public Ingredient() {}

    public Ingredient(String name, int inventory, double price) {
        this.name = name;
        this.inventory = inventory;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }

    public String getName() {
        return name;
    }

    public Integer getInventory() {
        return inventory;
    }

    public Double getPrice() {
        return price;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    @Transient
    public Long getIngredientId() {
        return id;
    }

}
