package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.database.CocktailDBClient;
import com.ezgroceries.shoppinglist.database.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.Cocktail;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {

    private final CocktailDBClient cocktailDBClient;

    public CocktailController(CocktailDBClient cocktailDBClient) {
        this.cocktailDBClient = cocktailDBClient;
    }

    @GetMapping
    public List<Cocktail> get(@RequestParam String search) {
        return  generateList(cocktailDBClient.searchCocktails(search));
    }

    //@GetMapping
    //public List<Cocktail> get(@RequestParam String search) {
    //return new ArrayList<>(getDummyResources());
    //}

    /*private List<Cocktail> getDummyResources() {
        return Arrays.asList(
                new Cocktail(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita",
                        "Cocktail glass",
                        "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                        "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt")),
                new Cocktail(
                        UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita",
                        "Cocktail glass",
                        "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                        "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt")));
    }*/
    private List<Cocktail> generateList(CocktailDBResponse response){
        List<Cocktail> listResponse;

        // map the list<DrinkResource> to the list<CocktailResource> Uses stream instead of for loop
        listResponse = response.getDrinks().stream().map(
                dbresponse -> new Cocktail(
                        UUID.randomUUID(),
                        dbresponse.getStrDrink(),
                        dbresponse.getStrGlass(),
                        dbresponse.getStrInstructions(),
                        dbresponse.getStrDrinkThumb(),
                        Stream.of(
                                dbresponse.getStrIngredient1(),
                                dbresponse.getStrIngredient2(),
                                dbresponse.getStrIngredient3(),
                                dbresponse.getStrIngredient4(),
                                dbresponse.getStrIngredient5()
                        ).filter(StringUtils::isNotBlank).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
        return listResponse;
    }
}