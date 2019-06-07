package com.ezgroceries.shoppinglist.database;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CocktailDBResponse {
    private List<Drink> drinks;

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
    public static class Drink {
        private String idDrink;
        private String Drink;
        private String Glass;
        private String Instructions;
        private String DrinkThumb;
        private String Ingredient1;
        private String Ingredient2;
        private String Ingredient3;
        private String Ingredient4;
        private String Ingredient5;

        public String getIdDrink() {
            return idDrink;
        }

        public void setIdDrink(String idDrink) {
            this.idDrink = idDrink;
        }

        public String getDrink() {
            return Drink;
        }

        public void setDrink(String Drink) {
            this.Drink = Drink;
        }

        public String getGlass() {
            return Glass;
        }

        public void setGlass(String Glass) {
            this.Glass = Glass;
        }

        public String getInstructions() {
            return Instructions;
        }

        public void setInstructions(String Instructions) {
            this.Instructions = Instructions;
        }

        public String getDrinkThumb() {
            return DrinkThumb;
        }

        public void setDrinkThumb(String DrinkThumb) {
            this.DrinkThumb = DrinkThumb;
        }

        public String getIngredient1() {
            return Ingredient1;
        }

        public void setIngredient1(String Ingredient1) {
            this.Ingredient1 = Ingredient1;
        }

        public String getIngredient2() {
            return Ingredient2;
        }

        public void setIngredient2(String Ingredient2) {
            this.Ingredient2 = Ingredient2;
        }

        public String getIngredient3() {
            return Ingredient3;
        }

        public void setIngredient3(String Ingredient3) {
            this.Ingredient3 = Ingredient3;
        }

        public String getIngredient4() {
            return Ingredient4;
        }

        public void setIngredient4(String Ingredient4) {
            this.Ingredient4 = Ingredient4;
        }

        public String getIngredient5() {
            return Ingredient5;
        }

        public void setIngredient5(String Ingredient5) {
            this.Ingredient5 = Ingredient5;
        }

        public Set<String> getIngredients() {
            Set<String> ingredients = new HashSet<>();
            ingredients.add(getIngredient1());
            ingredients.add(getIngredient2());
            ingredients.add(getIngredient3());
            ingredients.add(getIngredient4());
            ingredients.add(getIngredient5());
            return ingredients.stream().filter(i -> !i.isEmpty()).collect(Collectors.toSet());
        }
    }
}
