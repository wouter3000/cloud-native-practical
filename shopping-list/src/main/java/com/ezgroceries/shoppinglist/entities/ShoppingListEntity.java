package com.ezgroceries.shoppinglist.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "shopping_list")
public class ShoppingListEntity {

    @Id
    private UUID id;
    private String name;

    @ManyToMany
    @JoinTable(name = "cocktail_shopping_list",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "cocktail_id"))
    private Set<CocktailEntity> cocktails;

    public ShoppingListEntity() {

    }

    public ShoppingListEntity(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<CocktailEntity> getCocktails() {
        return cocktails;
    }

    public void setCocktails(Set<CocktailEntity> cocktails) {
        this.cocktails.addAll(cocktails);
    }
}