package com.ezgroceries.shoppinglist.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ShoppingList {
    private UUID shoppingListId;
    private String name;
    private Set<String> ingredients;

    public ShoppingList(UUID shoppingListId, String name, Set<String> ingredients) {
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

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }}
