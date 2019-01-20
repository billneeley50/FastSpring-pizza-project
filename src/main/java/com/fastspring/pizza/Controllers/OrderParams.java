package com.fastspring.pizza.Controllers;


public class OrderParams {

    public String[] getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPizzasize() {
        return pizzasize;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPizzasize(String pizzasize) {
        this.pizzasize = pizzasize;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public String getDiscountpercent() {
        return discountpercent;
    }

    public void setDiscountpercent(String discountpercent) {
        this.discountpercent = discountpercent;
    }

    private String[] ingredients = new String[0];
    private String name = "";
    private String address = "";
    private String phonenumber = "";
    private String price = "0.00";
    private String pizzasize = "LARGE";
    private String promocode = null;
    private String discountpercent = "0";

}
