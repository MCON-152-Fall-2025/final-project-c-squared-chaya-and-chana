package com.mcon152.recipeshare.service;

import com.mcon152.recipeshare.domain.Recipe;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class TitleAscendingSortStrategy implements RecipeSortStrategy {

    @Override
    public List<Recipe> sort(List<Recipe> recipes) {
        List<Recipe> sorted = new ArrayList<>(recipes);
        sorted.sort(Comparator.comparing(Recipe::getTitle, String.CASE_INSENSITIVE_ORDER));
        return sorted;
    }
}