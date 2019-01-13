package com.fastspring.pizza.Domain;


import javax.persistence.*;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "ingredients")

public class Ingredient {

    private @Id @GeneratedValue Long id;
    private String name;
    private Integer inventory;
    private Double price;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "ingredients")
    private Set<Pizza> pizzas = new HashSet<>();


    private Ingredient() {}

    public Ingredient(String name, int inventory, double price) {
        this.name = name;
        this.inventory = inventory;
        this.price = price;
    }


    public Long getId() {
        return id;
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

    public Set<Pizza> getPizzas() {
        return pizzas;
    }
}
