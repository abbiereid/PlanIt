package com.example.mobileappdevelopment;

public class Recipe {
        int id;
        String name;
        String ingredients;
        String instructions;

        public Recipe(int id, String name, String ingredients, String instructions) {
            this.id = id;
            this.name = name;
            this.ingredients = ingredients;
            this.instructions = instructions;
        }


    public int getID() {
            return id;
        }

        public String getName(){
            return name;
        }

        public String getIngredients() {
            return ingredients;
        }

        public String getInstructions() {
            return instructions;
        }

    public void setId(int parseInt) {
            this.id = id;
    }

    public void setName(String string) {
            this.name = name;
    }

    public void setIngredients(String string) {
            this.ingredients = ingredients;
    }

    public void setInstructions(String string) {
            this.instructions = instructions;
    }
}
