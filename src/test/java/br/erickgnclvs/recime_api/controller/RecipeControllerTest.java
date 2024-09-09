package br.erickgnclvs.recime_api.controller;

import br.erickgnclvs.recime_api.dto.RecipeDTO;
import br.erickgnclvs.recime_api.exception.ResponseException;
import br.erickgnclvs.recime_api.model.Difficulty;
import br.erickgnclvs.recime_api.service.RecipeService;
import br.erickgnclvs.recime_api.util.TestDataFactory;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@WebMvcTest
public class RecipeControllerTest {

    @MockBean
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    private List<RecipeDTO> createMockRecipes() {
        RecipeDTO recipe1 = TestDataFactory.createRecipeDTO(1L, "Recipe1", Difficulty.EASY, "http://example.com/image1.jpg", 90, 1);
        RecipeDTO recipe2 = TestDataFactory.createRecipeDTO(2L, "Recipe2", Difficulty.MEDIUM, "http://example.com/image2.jpg", 80, 2);
        return Arrays.asList(recipe1, recipe2);
    }

    @Test
    public void testGetTrendingRecipes() {
        List<RecipeDTO> recipes = createMockRecipes();
        when(recipeService.getTrendingRecipes()).thenReturn(recipes);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/recipes/trending")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("[0].name", equalTo("Recipe1"))
                .body("[1].name", equalTo("Recipe2"));
    }

    @Test
    public void testGetFilteredRecipes_Success() {
        List<RecipeDTO> recipes = createMockRecipes();
        when(recipeService.getFilteredRecipes("EASY")).thenReturn(recipes);

        given()
                .param("difficulty", "EASY")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/recipes/trending/filter")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("[0].name", equalTo("Recipe1"))
                .body("[1].name", equalTo("Recipe2"));
    }

    @Test
    public void testGetFilteredRecipes_InvalidDifficulty() {
        when(recipeService.getFilteredRecipes("INVALID"))
                .thenThrow(new ResponseException("Invalid difficulty provided", HttpStatus.BAD_REQUEST));

        given()
                .param("difficulty", "INVALID")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/recipes/trending/filter")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("status", equalTo(400))
                .body("message", equalTo("Invalid difficulty provided"));
    }

    @Test
    public void testGetFilteredRecipes_NoResults() {
        when(recipeService.getFilteredRecipes("HARD")).thenReturn(Arrays.asList());

        given()
                .param("difficulty", "HARD")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/recipes/trending/filter")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("size()", equalTo(0));
    }

    @Test
    public void testGetTrendingRecipes_Empty() {
        when(recipeService.getTrendingRecipes()).thenReturn(Arrays.asList());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/recipes/trending")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("size()", equalTo(0));
    }
}
