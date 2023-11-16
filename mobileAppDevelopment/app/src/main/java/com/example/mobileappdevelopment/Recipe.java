package com.example.mobileappdevelopment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Recipe {
    String recipeID;
    String recipeName;
    ArrayList<String> ingredients;
    ArrayList<String> instructions;

    //constructor
    public Recipe(String recipeID, String recipeName, ArrayList<String> ingredients, ArrayList<String> instructions) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    //getters and setters
    public String getRecipeID(){
        return recipeID;
    }

    public void setRecipeID() {
        this.recipeID = recipeID;
    }

    public String getRecipeName(){
        return recipeName;
    }

    public void setRecipeName() {
        this.recipeName = recipeName;
    }

    public ArrayList<String> getIngredients(){
        return ingredients;
    }

    public void setIngredients() {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getInstructions(){
        return instructions;
    }

    public void setInstructions() {
        this.instructions = instructions;
    }

}
