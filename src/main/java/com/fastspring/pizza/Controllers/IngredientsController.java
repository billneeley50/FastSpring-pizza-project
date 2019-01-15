package com.fastspring.pizza.Controllers;

import com.fastspring.pizza.Domain.Ingredient;
import com.fastspring.pizza.Domain.IngredientsRepository;
import com.fastspring.pizza.IngredientItem;
import com.fastspring.pizza.PizzaItem;
import com.fastspring.pizza.Services.IngredientsService;
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
    IngredientsService ingredientsService;

    @RequestMapping(value = "/addingredient", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public IngredientItem addingredient(@RequestBody IngredientParams params) {

        return ingredientsService.addIngredient(params);
    }


    @RequestMapping(value = "/updateingredient", method = RequestMethod.PUT)
    @Transactional(readOnly = false)
    public IngredientItem updateingredient(@RequestBody IngredientParams params) {

        return ingredientsService.upsertIngredient(params);
    }


}