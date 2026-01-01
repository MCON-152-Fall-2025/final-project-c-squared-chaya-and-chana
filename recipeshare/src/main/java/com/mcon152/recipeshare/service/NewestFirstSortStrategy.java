package com.mcon152.recipeshare.service;

import com.mcon152.recipeshare.domain.Recipe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NewestFirstSortStrategy implements RecipeSortStrategy {

    @Override
    public List<Recipe> sort(List<Recipe> recipes) {
        List<Recipe> sorted = new ArrayList<>(recipes);
        sorted.sort(Comparator.comparing(Recipe::getCreatedAt).reversed());
        return sorted;
    }
}
