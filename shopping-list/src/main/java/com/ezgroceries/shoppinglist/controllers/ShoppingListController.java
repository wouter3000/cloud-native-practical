package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/shopping-lists", produces = "application/json")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList createShoppingList(@RequestBody ShoppingList shoppingList) {
        return shoppingListService.createShoppingList(shoppingList);
    }
    //@PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    //public ShoppingList createShoppingList(@RequestBody Map<String, String> body) {
        //ShoppingList shoppingList = shoppingListService.createShoppingList(body.get("name"));
        //return shoppingList;
    //}
    @PostMapping(value = "/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, String>> addCocktails(@PathVariable String shoppingListId, @RequestBody List<Map<String, String>> body) {
        List<String> cocktails = body.stream().map(map -> map.get("cocktailId")).collect(Collectors.toList());
        shoppingListService.addCocktails(shoppingListId, cocktails);
        return body.subList(0, 1);
    }

    @GetMapping(value = "/{shoppingListId}")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingList getShoppingList(@PathVariable String shoppingListId) {
        return shoppingListService.get(shoppingListId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListService.getAllShoppingLists();
    }

    /*
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
    } */
}