package com.example.mobileappdevelopment;

public class Recipe {
        int id;
        String name;
        String instructions;

        public Recipe(int id, String name, String instructions) {
            this.id = id;
            this.name = name;
            this.instructions = instructions;
        }


    public int getID() {
            return id;
        }

        public String getName(){
            return name;
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

    public void setInstructions(String string) {
            this.instructions = instructions;
    }
}
