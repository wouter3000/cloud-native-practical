package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.repositories.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailService cocktailService;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailService cocktailService) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailService = cocktailService;
    }

    public ShoppingList createShoppingList(String name) {
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity(name);
        ShoppingListEntity saved = shoppingListRepository.save(shoppingListEntity);
        return fromShoppingListEntity(saved);
    }

    public ShoppingList get(String shoppingListId) {
        Optional<ShoppingListEntity> shoppingList = shoppingListRepository.findById(UUID.fromString(shoppingListId));
        return shoppingList.map(this::fromShoppingListEntity)
                .orElseThrow(() -> new RuntimeException("Shopping list with id '" + shoppingListId + "' not found"));
    }

    public ShoppingList addCocktails(String shoppingListId, List<String> cocktails) {
        List<CocktailEntity> cocktailEntities = cocktailService.getAllById(cocktails);
        return shoppingListRepository.findById(UUID.fromString(shoppingListId)).map(shoppingList -> {
            shoppingList.setCocktails(cocktailEntities);
            shoppingListRepository.save(shoppingList);
            return fromShoppingListEntity(shoppingList);
        }).orElseThrow(() -> new RuntimeException("Shopping list with id '" + shoppingListId + "' not found"));
    }

    public List<ShoppingList> getAllShoppingLists() {
        List<ShoppingListEntity> entity = shoppingListRepository.findAll();
        return entity.stream().map(this::fromShoppingListEntityWithIngredients).collect(Collectors.toList());
    }

    private ShoppingList fromShoppingListEntity(ShoppingListEntity shoppingListEntity) {
        return new ShoppingList(shoppingListEntity.getId(), shoppingListEntity.getName());
    }

    private ShoppingList fromShoppingListEntityWithIngredients(ShoppingListEntity entity){
        ShoppingList shoppingList = fromShoppingListEntity(entity);
        List<CocktailEntity> entities = (entity.getCocktails() != null) ? entity.getCocktails() : new ArrayList<>();
        List<String> ids = entities.stream().map(CocktailEntity::getId).map(UUID::toString).collect(Collectors.toList());
        List<String> ingredients = cocktailService.getAllById(ids).stream().map(CocktailEntity::getIngredients).flatMap(Set::stream).distinct().collect(Collectors.toList());
        shoppingList.setIngredients(ingredients);
        return shoppingList;
    }

}