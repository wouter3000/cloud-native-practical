package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.repositories.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailService cocktailService;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailService cocktailService) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailService = cocktailService;
    }

    public ShoppingList getSpecificShoppingList(UUID shoppingListId) {
        ShoppingListEntity shoppingListEntity = getShoppingListEntity(shoppingListId);
        ShoppingList shoppingList = transformShoppingList(shoppingListEntity);
        return shoppingList;
    }

    public List<ShoppingList> getAllShoppingLists() {
        List<ShoppingList> shoppingLists = new ArrayList<>();
        for (ShoppingListEntity shoppingListEntity : shoppingListRepository.findAll()) {
            shoppingLists.add(transformShoppingList(shoppingListEntity));
        }
        return shoppingLists;
    }

    public ShoppingList createShoppingList(ShoppingList shoppingList) {
        ShoppingListEntity shoppingListEntity = shoppingListRepository.save(new ShoppingListEntity(shoppingList.getName()));
        return transformShoppingList(shoppingListEntity);
    }

    public ShoppingList addCocktailsToShoppingList(UUID shoppingListId, List<Cocktail> cocktails) {
        //Set<CocktailEntity> cocktailEntities = cocktailService.findCocktailEntitiesById(cocktails);
        ShoppingListEntity shoppingListEntity = getShoppingListEntity(shoppingListId);
        //shoppingListEntity.setCocktails(cocktailEntities);
        shoppingListRepository.save(shoppingListEntity);
        return transformShoppingList(shoppingListEntity);
    }

    private ShoppingList transformShoppingList(ShoppingListEntity shoppingListEntity) {
        Set<String> ingredients = shoppingListEntity.getCocktails().stream()
                .map(CocktailEntity::getIngredients)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        ShoppingList shoppingList = new ShoppingList(shoppingListEntity.getId(), shoppingListEntity.getName());
        shoppingList.setIngredients(ingredients);

        return shoppingList;
    }

    private ShoppingListEntity getShoppingListEntity(UUID shoppingListId) {
        return shoppingListRepository
                .findById(shoppingListId)
                .orElseThrow(() -> new RuntimeException("No shopping list found for ID: " + shoppingListId));
    }

}