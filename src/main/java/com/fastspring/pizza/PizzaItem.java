package com.fastspring.pizza;

import com.fastspring.pizza.Domain.Ingredient;
import com.fastspring.pizza.Domain.Pizza;
import com.fastspring.pizza.Domain.PizzaSize;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class PizzaItem extends DomainItem {

    private String customerName = "";
    private String customerAddress = "";
    private String customerNumber = "";
    private PizzaSize.PIZZASIZE pizzaSize = PizzaSize.PIZZASIZE.LARGE;
    private Double totalPrice = 0.00;
    private List<Ingredient> ingredients = new LinkedList<>();
    private String orderDate = "";

    public PizzaItem() {

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public PizzaSize.PIZZASIZE getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(PizzaSize.PIZZASIZE pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public PizzaItem(Pizza source) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        this.customerName = source.getCustomerName();
        this.customerAddress = source.getCustomerAddress();
        this.customerNumber = source.getCustomerNumber();
        this.pizzaSize = source.getPizzaSize().getSize();
        this.totalPrice = source.getTotalPrice();
        this.ingredients = source.getIngredients();
        this.orderDate = dataFormat.format(source.getCreateDate());

    }
}
