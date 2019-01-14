package com.fastspring.pizza.Services;

import com.fastspring.pizza.Domain.Ingredient;
import com.fastspring.pizza.Domain.IngredientsRepository;
import com.fastspring.pizza.Domain.Pizza;
import com.fastspring.pizza.Domain.PizzaRepository;
import com.fastspring.pizza.Domain.PizzaSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;


@Service
public class OrderService {


    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Transactional(readOnly = false)
    public Pizza placeOrder(String name,
                            String address,
                            String phonenumber,
                            double price,
                            String pizzasize,
                            List<String> ingredients

                           ) throws Exception {

        Iterable<Ingredient> ingredients1 = ingredientsRepository.findAll();
        Iterator<Ingredient> iterator = ingredients1.iterator();

        Pizza pizza = new Pizza(name, address, phonenumber, PizzaSize.PIZZASIZE.valueOf(pizzasize), price );

        while(iterator.hasNext()) {
            Ingredient ingredient = iterator.next();
            if (ingredient.getInventory() == 0) {
                throw new Exception("Sorry.  We are out of " + ingredient.getName() + ".");
            }
            if (ingredients.contains(ingredient.getName())) {
                ingredientsRepository.decrementInventory(ingredient.getId());
            }
        }

        Pizza savedPizza = pizzaRepository.save(pizza);
        return savedPizza;
    }
}
