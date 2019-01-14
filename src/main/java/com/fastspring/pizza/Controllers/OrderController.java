package com.fastspring.pizza.Controllers;

import com.fastspring.pizza.Domain.Pizza;
import com.fastspring.pizza.PizzaItem;
import com.fastspring.pizza.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/bodyorder", method = RequestMethod.POST)
    public PizzaItem bodyorder(@RequestBody OrderParams params) {

        if (params != null) {
            for (int i = 0; i < params.getIngredients().length; i++) {
                System.out.println("ingredient: " + params.getIngredients()[i]);
            }
        }

        try {
            List<String> ingredients = new LinkedList<String>();
            for (int i = 0; i < params.getIngredients().length; i++) {
                ingredients.add(params.getIngredients()[i]);
            }


            Pizza newPizza = orderService.placeOrder(params.getName(), params.getAddress(),
                    params.getPhonenumber(), Double.valueOf(params.getPrice()), params.getPizzasize(), ingredients);
            PizzaItem pizzaItem = new PizzaItem(newPizza);
            pizzaItem.setMessage(params.getName() + ", your " + params.getPizzasize().toLowerCase() +
                    " pizza will be delivered to " +
                    params.getAddress() + " within 30 minutes.  " +
                    "If there are any delays we will call you at " + params.getPhonenumber() + ".");
            return pizzaItem;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            PizzaItem errorPizza = new PizzaItem();
            errorPizza.setMessage(e.getMessage());
            return errorPizza;
        }

    }


}