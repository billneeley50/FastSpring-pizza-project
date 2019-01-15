package com.fastspring.pizza;

public class DomainItem {

    private String message = "";

    protected DomainItem() {}

    protected DomainItem(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
