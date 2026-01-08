package com.mcon152.recipeshare;

import com.mcon152.recipeshare.domain.BasicRecipe;
import com.mcon152.recipeshare.domain.DairyRecipe;
import com.mcon152.recipeshare.domain.DessertRecipe;
import com.mcon152.recipeshare.domain.Recipe;
import com.mcon152.recipeshare.repository.RecipeRepository;
import com.mcon152.recipeshare.service.RecipeService;
import com.mcon152.recipeshare.service.RecipeServiceImpl;
import com.mcon152.recipeshare.service.ServingsAscendingSortStrategy;
import com.mcon152.recipeshare.web.RecipeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecipeShareApplicationTests {

    private RecipeRepository repo;
    private RecipeServiceImpl service;
    private List<Recipe> recipes;

    @BeforeEach
    void setUp() {
        repo = mock(RecipeRepository.class);

        DessertRecipe cake = new DessertRecipe(
                1L, "Chocolate cake", "Fluffy chocolate cake",
                "eggs, flour, oil, cocoa", "add, mix, bake", 10
        );
        BasicRecipe soup = new BasicRecipe(
                2L, "Chicken soup", "Classic soup",
                "chicken, veggies, water, spices", "boil for 2 hours", 7
        );
        DairyRecipe lasagna = new DairyRecipe(
                3L, "Lasagna", "Easy dinner",
                "pasta, sauce, cheese, veggies", "layer, cook, enjoy", 6
        );

        cake.setCreatedAt(LocalDateTime.now().minusDays(2));
        soup.setCreatedAt(LocalDateTime.now().minusDays(1));
        lasagna.setCreatedAt(LocalDateTime.now());

        recipes = List.of(cake, soup, lasagna);

        when(repo.findAll()).thenReturn(recipes);
        service = new RecipeServiceImpl(repo);
    }

    @Test
    void ServingsAscendingSortStrategyTest() {
        List<Recipe> sorted = service.getAllRecipes("servings");
        assertEquals(3, sorted.size());
        assertEquals(6, sorted.get(0).getServings());
        assertEquals(7, sorted.get(1).getServings());
        assertEquals(10, sorted.get(2).getServings());
    }

    @Test
    void TitleAscendingSortStrategyTest() {
        List<Recipe> sorted = service.getAllRecipes("title");
        assertEquals(3, sorted.size());
        assertEquals("Chicken soup", sorted.get(0).getTitle());
        assertEquals("Chocolate cake", sorted.get(1).getTitle());
        assertEquals("Lasagna", sorted.get(2).getTitle());
    }

    @Test
    void NewestFirstSortStrategyTest() {
        List<Recipe> sorted = service.getAllRecipes("newest");
        assertEquals(3, sorted.size());
        assertEquals("Lasagna", sorted.get(0).getTitle());
        assertEquals("Chicken soup", sorted.get(1).getTitle());
        assertEquals("Chocolate cake", sorted.get(2).getTitle());
    }

    @Test
    void noStrategyTest() {
        List<Recipe> sorted = service.getAllRecipes();
        assertEquals(3, sorted.size());
        assertEquals("Chocolate cake", sorted.get(0).getTitle());
        assertEquals("Chicken soup", sorted.get(1).getTitle());
        assertEquals("Lasagna", sorted.get(2).getTitle());
    }

    @Test
    void EmptyStringStrategyTest() {
        List<Recipe> sorted = service.getAllRecipes("");
        assertEquals(3, sorted.size());
        assertEquals("Chocolate cake", sorted.get(0).getTitle());
        assertEquals("Chicken soup", sorted.get(1).getTitle());
        assertEquals("Lasagna", sorted.get(2).getTitle());
    }
}
