package com.mcon152.recipeshare.service;

import com.mcon152.recipeshare.domain.Recipe;

import java.util.Comparator;
import java.util.List;

public class TitleAscendingSort implements RecipeSortStrategy {
    public List<Recipe> sort(List<Recipe> recipes) {
        recipes.sort(Comparator.comparing(Recipe::getTitle, String.CASE_INSENSITIVE_ORDER));
        return recipes;
    }

}
