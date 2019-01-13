package com.fastspring.pizza.Controllers;

import com.fastspring.pizza.Domain.Pizza;
import com.fastspring.pizza.PizzaItem;
import com.fastspring.pizza.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/neworder", method = RequestMethod.POST)
    public PizzaItem neworder(@RequestParam(value = "name") String name,
                              @RequestParam(value = "address") String address,
                              @RequestParam(value = "phonenumber") String phonenumber,
                              @RequestParam(value = "pizzasize") String pizzasize,
                              @RequestParam(value = "cheese", defaultValue = "false") boolean cheese,
                              @RequestParam(value = "pepperoni", defaultValue = "false") boolean pepperoni,
                              @RequestParam(value = "sausage", defaultValue = "false") boolean sausage,
                              @RequestParam(value = "mushrooms", defaultValue = "false") boolean mushrooms,
                              @RequestParam(value = "olives", defaultValue = "false") boolean olives,

                              final RedirectAttributes redirectAttributes
    ) {

        try {
            List<String> ingredients = new LinkedList<String>();
            if (cheese) ingredients.add("Cheese");
            if (pepperoni) ingredients.add("Pepperoni");
            if (sausage) ingredients.add("Sausage");
            if (olives) ingredients.add("Olives");
            if (mushrooms) ingredients.add("Mushrooms");

            Pizza newPizza = orderService.placeOrder(name, pizzasize, ingredients);
            PizzaItem pizzaItem = new PizzaItem(newPizza);
            pizzaItem.setMessage(name + ", your " + pizzasize.toLowerCase() + " pizza will be delivered to " +
                    address + " within 30 minutes.  " +
                    "If there are any delays we will call you at " + phonenumber + ".");
            return pizzaItem;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute(e.getMessage());
            PizzaItem errorPizza = new PizzaItem();
            errorPizza.setMessage(e.getMessage());
            return errorPizza;
        }

    }

    private void setIngredient(String ingredient, List<String> ingredients, String name) {

    }

}