package com.fastspring.pizza.Services;

import com.fastspring.pizza.Controllers.IngredientParams;
import com.fastspring.pizza.Domain.*;
import com.fastspring.pizza.IngredientItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;


@Service
public class IngredientsService {


    @Autowired
    private IngredientsRepository ingredientsRepository;


    @Transactional(readOnly = false)
    public IngredientItem addIngredient(IngredientParams params) {

        try {
            Ingredient theIngredient = new Ingredient(params.getName(),
                    Integer.valueOf(params.getInventory()),
                    Double.valueOf(params.getPrice()));
            ingredientsRepository.save(theIngredient);
            return new IngredientItem(theIngredient);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            IngredientItem ingredientItem = new IngredientItem();
            ingredientItem.setMessage(e.getMessage());
            return ingredientItem;
        }


    }

    @Transactional(readOnly = false)
    public IngredientItem upsertIngredient(IngredientParams params) {

        try {
            Ingredient theIngredient = ingredientsRepository.findByName(params.getName());
            if (theIngredient == null) {
                System.out.println("Adding: " + params.getName());
                Ingredient newIngredient = new Ingredient(params.getName(),
                        Integer.valueOf(params.getInventory()),
                        Double.valueOf(params.getPrice()));
                theIngredient = ingredientsRepository.save(newIngredient);
            } else {
                ingredientsRepository.updateIngredient(params.getName(),
                        Integer.valueOf(params.getInventory()),
                        Double.valueOf(params.getPrice()));
            }
            return new IngredientItem(theIngredient);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            IngredientItem ingredientItem = new IngredientItem(e.getMessage());
            return ingredientItem;
        }

    }

    public Ingredient[] getIngredients() {

        Iterator iterator = ingredientsRepository.findAll().iterator();
        LinkedList<Ingredient> ingredients = new LinkedList<>();

        while(iterator.hasNext()) {
            ingredients.add((Ingredient)iterator.next());
        }

        return ingredients.toArray(new Ingredient[]{});
    }

}
