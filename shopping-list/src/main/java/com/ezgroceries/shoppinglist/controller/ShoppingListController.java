package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/shopping-lists", produces = "application/json")
public class ShoppingListController {

    // Create a new Shopping List
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList createShoppingList(@RequestBody ShoppingList newShoppingList) {
        newShoppingList.setShoppingListId(UUID.randomUUID());
        return newShoppingList;
    }

    // Add Cocktails to Shopping List
    @PostMapping(value = "/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Cocktail> addCocktailsToShoppingList(@PathVariable String shoppingListId, @RequestBody List<Cocktail> cocktails) {
        return cocktails;
    }

    // Get a Shopping List
    @GetMapping(value = "/{shoppingListId}")
    public ShoppingList getShoppingList(@PathVariable UUID shoppingListId) {
        return new ShoppingList(
                UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f"),
                "Stephanie's Birthday",
                Arrays.asList("Tequila", "Triple Sec", "Lime Juice", "Salt", "Blue Curacao")
        );
    }

    // Get all Shopping Lists
    @GetMapping
    public List<ShoppingList> getAllShoppingLists()  {
        return getResources();
    }

    private List<ShoppingList> getResources() {
        return Arrays.asList(
                new ShoppingList(
                        UUID.fromString("4ba92a46-1d1b-4e52-8e38-13cd56c7224c"),
                        "Stephanie's Birthday",
                        Arrays.asList("Tequila", "Triple Sec", "Lime Juice", "Salt", "Blue Curacao")
                ),
                new ShoppingList(
                        UUID.fromString("6c7d09c2-8a25-4d54-a979-25ae779d2465"),
                        "My Birthday",
                        Arrays.asList("Tequila", "Triple Sec", "Lime Juice", "Salt", "Blue Curacao")
                )
        );
    }
}