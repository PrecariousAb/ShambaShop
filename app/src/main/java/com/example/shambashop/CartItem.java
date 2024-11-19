package com.example.shambashop;

public class CartItem {
    private String itemId;
    private String name;
    private double price;

    // Default constructor (needed for Firebase)
    public CartItem() {
    }

    public CartItem(String itemId, String name, double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}