package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.repositories.ShoppingListRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    //public ShoppingList createShoppingList(ShoppingList shoppingList) {
        //ShoppingListEntity shoppingListEntity = new ShoppingListEntity(shoppingList.getName());
        //ShoppingListEntity saved = shoppingListRepository.save(shoppingListEntity);
        //return ShoppingListEntity(saved);
    //}

}