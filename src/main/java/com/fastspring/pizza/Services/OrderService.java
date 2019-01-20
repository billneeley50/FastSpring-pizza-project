package com.fastspring.pizza.Services;

import com.fastspring.pizza.Controllers.IngredientParams;
import com.fastspring.pizza.Domain.*;
import com.fastspring.pizza.PizzaItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {


    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    PromotionsRepository promotionsRepository;

    @Autowired
    PizzaSizeRepository pizzaSizeRepository;

    @Transactional(readOnly = false)
    public Pizza placeOrder(String name,
                            String address,
                            String phonenumber,
                            double price,
                            String size,
                            String promoCode,
                            int discountPercent,
                            List<String> ingredients

    ) throws Exception {

        Iterable<Ingredient> ingredients1 = ingredientsRepository.findAll();
        Iterator<Ingredient> iterator = ingredients1.iterator();

        Promotion promotion = null;
        if (promoCode != null) {
            promotion = promotionsRepository.findByPromotionCode(promoCode);
        }
        PizzaSize pizzaSize = pizzaSizeRepository.findBySize(PizzaSize.PIZZASIZE.valueOf(size));

        Pizza pizza = new Pizza(name, address, phonenumber, pizzaSize, promotion, discountPercent, price);

        while (iterator.hasNext()) {
            Ingredient ingredient = iterator.next();

            if (ingredients.contains(ingredient.getName().toLowerCase())) {

                if (ingredient.getInventory() == 0) {
                    throw new Exception("Sorry.  We are out of " + ingredient.getName() + ".");
                }

                ingredientsRepository.decrementInventory(ingredient.getId());

                pizza.addIngredient(ingredient);
                ingredient.addPizza(pizza);
            }
        }

        Pizza savedPizza = pizzaRepository.save(pizza);
        return savedPizza;
    }


    @Transactional(readOnly = false)
    public Ingredient updateingredient(IngredientParams params) {

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
            return theIngredient;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}
