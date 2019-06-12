package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.database.CocktailDBClient;
import com.ezgroceries.shoppinglist.database.CocktailDBResponse;
import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.repositories.CocktailRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CocktailService {


    private final CocktailRepository cocktailRepository;
    private final CocktailDBClient cocktailDBClient;

    public CocktailService(CocktailRepository cocktailRepository, CocktailDBClient cocktailDBClient) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailDBClient = cocktailDBClient;
    }

    public List<Cocktail> searchCocktails(String search) {
        CocktailDBResponse response = cocktailDBClient.searchCocktails(search);
        return mergeCocktails(response.getDrinks());
    }

    public List<CocktailEntity> getAllById(List<String> cocktails) {
        return cocktailRepository.findAllById(cocktails.stream().map(UUID::fromString).collect(Collectors.toList()));
    }

    private List<Cocktail> mergeCocktails(List<CocktailDBResponse.Drink> drinks) {
        // Get all the idDrink attributes
        List<String> ids = drinks.stream().map(CocktailDBResponse.Drink::getIdDrink).collect(Collectors.toList());

        // Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids).stream()
                .collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        // Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailEntity newCocktailEntity = new CocktailEntity(UUID.randomUUID(), drinkResource.getIdDrink(), drinkResource.getDrink());
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        // Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<Cocktail> mergeAndTransform(List<CocktailDBResponse.Drink> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream().map(drinkResource -> new Cocktail(allEntityMap.get(drinkResource.getIdDrink()).getId(), drinkResource.getDrink(),
                drinkResource.getGlass(),
                drinkResource.getInstructions(), drinkResource.getDrinkThumb(), getIngredients(drinkResource))).collect(Collectors.toList());
    }

    private List<String> getIngredients(CocktailDBResponse.Drink drinkResource) {
        return Arrays.asList(
                drinkResource.getIngredient1(),
                drinkResource.getIngredient2(),
                drinkResource.getIngredient3(),
                drinkResource.getIngredient4(),
                drinkResource.getIngredient5()
        );
    }

}