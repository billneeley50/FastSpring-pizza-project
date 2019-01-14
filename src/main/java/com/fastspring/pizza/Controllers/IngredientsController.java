package com.fastspring.pizza.Controllers;

import com.fastspring.pizza.Domain.Ingredient;
import com.fastspring.pizza.Domain.IngredientsRepository;
import com.fastspring.pizza.PizzaItem;
import com.fastspring.pizza.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class IngredientsController {

    @Autowired
    IngredientsRepository ingredientsRepository;

    @Autowired
    OrderService orderService;


    @RequestMapping(value = "/addingredient", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public Ingredient addingredient(@RequestBody IngredientParams params) {

        System.out.println("Adding: " + params.getName());


        try {
            Ingredient theIngredient = new Ingredient(params.getName(),
                    Integer.valueOf(params.getInventory()),
                    Double.valueOf(params.getPrice()));
            ingredientsRepository.save(theIngredient);
            return theIngredient;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            PizzaItem errorPizza = new PizzaItem();
            errorPizza.setMessage(e.getMessage());
            return null;
        }

    }


    @RequestMapping(value = "/updateingredient", method = RequestMethod.PUT)
    @Transactional(readOnly = false)
    public Ingredient updateingredient(@RequestBody IngredientParams params) {

        return orderService.updateingredient(params);
    }


}