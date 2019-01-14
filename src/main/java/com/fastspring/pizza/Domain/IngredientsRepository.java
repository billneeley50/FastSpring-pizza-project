package com.fastspring.pizza.Domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsRepository extends CrudRepository<Ingredient, Long> {


    public Ingredient findByName(String name);

    @Modifying
    @Query("UPDATE Ingredient SET inventory = (inventory - 1) WHERE id = :id")
    public void decrementInventory(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Ingredient SET inventory = :inventory, price = :price WHERE name = :name")
    public void updateIngredient(@Param("name") String name, @Param("inventory") int inventory, @Param("price") double price);

}
