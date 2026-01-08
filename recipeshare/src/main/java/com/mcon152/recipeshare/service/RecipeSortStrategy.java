package com.mcon152.recipeshare.service;

import com.mcon152.recipeshare.domain.Recipe;

import java.util.Comparator;
import java.util.List;

public interface RecipeSortStrategy {
    List<Recipe> sort(List<Recipe> recipes);
}
