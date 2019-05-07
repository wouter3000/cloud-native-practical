package com.ezgroceries.shoppinglist.model;

import java.util.List;
import java.util.UUID;

public class ShoppingList {
    private UUID shoppingListId;
    private String name;
    private List ingredients;

    public ShoppingList(UUID shoppingListId, String name, List ingredients) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.ingredients = ingredients;
    }

    public UUID getShoppingListID() {
        return shoppingListId;
    }

    public String getName() {
        return name;
    }

    public List getIngredients() {
        return ingredients;
    }
}
