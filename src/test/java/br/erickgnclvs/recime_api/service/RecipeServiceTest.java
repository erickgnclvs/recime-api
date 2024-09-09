package br.erickgnclvs.recime_api.service;

import br.erickgnclvs.recime_api.dto.RecipeDTO;
import br.erickgnclvs.recime_api.model.Difficulty;
import br.erickgnclvs.recime_api.model.Recipe;
import br.erickgnclvs.recime_api.repository.RecipeRepository;
import br.erickgnclvs.recime_api.exception.ResponseException;
import br.erickgnclvs.recime_api.util.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTrendingRecipes() {
        Recipe recipe1 = TestDataFactory.createRecipe(1L, "Recipe1", Difficulty.EASY, "http://example.com/image1.jpg", 90);
        Recipe recipe2 = TestDataFactory.createRecipe(2L, "Recipe2", Difficulty.MEDIUM, "http://example.com/image2.jpg", 85);
        when(recipeRepository.findAllByOrderByScoreDesc()).thenReturn(Arrays.asList(recipe1, recipe2));

        List<RecipeDTO> result = recipeService.getTrendingRecipes();

        assertNotNull(result);
        assertEquals(2, result.size());

        RecipeDTO dto1 = TestDataFactory.createRecipeDTO(1L, "Recipe1", Difficulty.EASY, "http://example.com/image1.jpg", 90, 1);
        RecipeDTO dto2 = TestDataFactory.createRecipeDTO(2L, "Recipe2", Difficulty.MEDIUM, "http://example.com/image2.jpg", 85, 2);

        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }

    @Test
    public void testGetFilteredRecipes_ValidDifficulty() {
        Recipe recipe = TestDataFactory.createRecipe(1L, "Recipe1", Difficulty.EASY, "http://example.com/image1.jpg", 90);
        when(recipeRepository.findByDifficultyOrderByScoreDesc(Difficulty.EASY)).thenReturn(Arrays.asList(recipe));

        List<RecipeDTO> result = recipeService.getFilteredRecipes("EASY");

        assertNotNull(result);
        assertEquals(1, result.size());

        RecipeDTO dto = TestDataFactory.createRecipeDTO(1L, "Recipe1", Difficulty.EASY, "http://example.com/image1.jpg", 90, 1);
        assertEquals(dto, result.get(0));
    }

    @Test
    public void testGetFilteredRecipes_InvalidDifficulty() {
        ResponseException exception = assertThrows(ResponseException.class, () -> {
            recipeService.getFilteredRecipes("INVALID");
        });
        assertEquals("Invalid difficulty provided. Valid options are: [EASY, MEDIUM, HARD]", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    public void testGetFilteredRecipes_NoDifficulty() {
        ResponseException exception = assertThrows(ResponseException.class, () -> {
            recipeService.getFilteredRecipes(null);
        });
        assertEquals("A difficulty is required for filtering trending recipes", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    public void testGetTrendingRecipes_NoRecipesFound() {
        when(recipeRepository.findAllByOrderByScoreDesc()).thenReturn(Arrays.asList());

        List<RecipeDTO> result = recipeService.getTrendingRecipes();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetTrendingRecipes_MultipleRecipesSameScore() {
        Recipe recipe1 = TestDataFactory.createRecipe(1L, "Recipe1", Difficulty.EASY, "http://example.com/image1.jpg", 90);
        Recipe recipe2 = TestDataFactory.createRecipe(2L, "Recipe2", Difficulty.EASY, "http://example.com/image2.jpg", 90);
        when(recipeRepository.findAllByOrderByScoreDesc()).thenReturn(Arrays.asList(recipe1, recipe2));

        List<RecipeDTO> result = recipeService.getTrendingRecipes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(90, result.get(0).getScore());
        assertEquals(90, result.get(1).getScore());
    }

    @Test
    public void testGetFilteredRecipes_HardDifficulty() {
        Recipe recipe = TestDataFactory.createRecipe(1L, "Recipe1", Difficulty.HARD, "http://example.com/image1.jpg", 95);
        when(recipeRepository.findByDifficultyOrderByScoreDesc(Difficulty.HARD)).thenReturn(Arrays.asList(recipe));

        List<RecipeDTO> result = recipeService.getFilteredRecipes("HARD");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Recipe1", result.get(0).getName());
        assertEquals(Difficulty.HARD, result.get(0).getDifficulty());
    }

    @Test
    public void testGetFilteredRecipes_NullRepositoryReturn() {
        when(recipeRepository.findByDifficultyOrderByScoreDesc(any(Difficulty.class))).thenReturn(null);

        List<RecipeDTO> result = recipeService.getFilteredRecipes("EASY");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetFilteredRecipes_EmptyList() {
        when(recipeRepository.findByDifficultyOrderByScoreDesc(Difficulty.EASY)).thenReturn(Arrays.asList());

        List<RecipeDTO> result = recipeService.getFilteredRecipes("EASY");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
