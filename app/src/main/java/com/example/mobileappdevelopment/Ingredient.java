package com.example.mobileappdevelopment;

public class Ingredient {
    int id;
    String name;
    int amount;

    public Ingredient(int id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}

