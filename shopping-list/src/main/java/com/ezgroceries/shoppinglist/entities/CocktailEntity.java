package com.ezgroceries.shoppinglist.entities;

import com.ezgroceries.shoppinglist.utils.StringSetConverter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cocktail")
public class CocktailEntity {

    @Id
    private UUID id;

    @Column(name = "id_drink")
    private String idDrink;

    private String name;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public CocktailEntity() {

    }

    public CocktailEntity(UUID id, String idDrink, String name, Set<String> ingredients) {
        this.id = id;
        this.idDrink = idDrink;
        this.name = name;
        this.ingredients = ingredients;
    }

    public UUID getId() {
        return id;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }
}