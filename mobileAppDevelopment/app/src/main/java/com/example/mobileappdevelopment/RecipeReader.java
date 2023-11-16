package com.example.mobileappdevelopment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class RecipeReader {
    public static ArrayList<Recipe> fillRecipeArrayList() {
        String filePath = "C:\\Users\\abbie\\mobileAppDevelopment\\app\\src\\main\\java\\com\\example\\mobileappdevelopment\\recipes";


        ArrayList<Recipe> recipes = new ArrayList<>();
        BufferedReader br= null;

        try {
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));

            String line = null;

            //read line as long as it's not blank.
            while ((line = br.readLine())!= null) {
                String[] part = line.split(":");

                String recipeID = part[0].trim();
                String recipeName = part[1].trim();
                String ingredientsString = part[2].trim();
                String instructionsString = part[3].trim();

                String[] ingredientsArray = ingredientsString.split(",");
                ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(ingredientsArray));

                String[] instructionsArray = instructionsString.split(",");
                ArrayList<String> instructions = new ArrayList<>(Arrays.asList(instructionsArray));

                Recipe recipe = new Recipe(recipeID, recipeName, ingredients, instructions);
                recipes.add(recipe);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return recipes;
    }
}
